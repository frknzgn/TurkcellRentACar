package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.payment.GetPaymentDto;
import com.turkcell.rentacar.business.dtos.payment.ListPaymentDto;
import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.payment.CreateExtraPaymentRequest;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.payment.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.payment.UpdatePaymentRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entites.concretes.Invoice;
import com.turkcell.rentacar.entites.concretes.Payment;
import com.turkcell.rentacar.entites.concretes.Rental;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private PosService posService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;
    private AdditionalServiceService additionalServiceService;
    private CarService carService;
    private InvoiceService invoiceService;
    private RentalService rentalService;
    private CustomerService customerService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,PosService posService, OrderedAdditionalServiceService orderedAdditionalServiceService,
                          AdditionalServiceService additionalServiceService,CarService carService,InvoiceService invoiceService,RentalService rentalService,
                          CustomerService customerService) {
    	
        this.paymentDao = paymentDao;
        this.modelMapperService = modelMapperService;
        this.posService = posService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
        this.additionalServiceService = additionalServiceService;
        this.carService = carService;
        this.invoiceService = invoiceService;
        this.rentalService = rentalService;
        this.customerService = customerService;
        
    }

    
    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) throws BusinessException {
    	
        double totalFee = totalFeeCalculator(createPaymentRequest);
        Integer invoiceId = PaymentSuccessor(createPaymentRequest, totalFee);
        
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        Payment payment = new Payment(0, totalFee, invoice);
        this.paymentDao.save(payment);
        return new SuccessResult(Messages.PAYMENT_ADDED);
        
    }

    @Override
    public Result update(UpdatePaymentRequest updatePaymentRequest) throws BusinessException {

        this.invoiceService.checkIfInvoiceIdExists(updatePaymentRequest.getInvoiceId());
        checkIfPaymentIdExist(updatePaymentRequest.getPaymentId());
        
        Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);
        this.paymentDao.save(payment);
        
        return new SuccessResult(Messages.PAYMENT_UPDATED);
    }

    @Override
    public Result delete(DeletePaymentRequest deletePaymentRequest) throws BusinessException {
    	
        checkIfPaymentIdExist(deletePaymentRequest.getPaymentId());
        
        Payment payment = this.modelMapperService.forRequest().map(deletePaymentRequest, Payment.class);
        this.paymentDao.deleteById(payment.getPaymentId());
        
        return new SuccessResult(Messages.PAYMENT_DELETED);
    }

    @Override
    public DataResult<List<ListPaymentDto>> getAll() {
    	
        List<Payment> response = this.paymentDao.findAll();
        
        List<ListPaymentDto> result = response.stream()
                .map(payment -> this.modelMapperService.forDto().
                		map(payment, ListPaymentDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(result, Messages.PAYMENT_GETALL);
    }

    @Override
    public DataResult<GetPaymentDto> getById(int paymentId) throws BusinessException {
    	
        checkIfPaymentIdExist(paymentId);
        
        Payment payment = this.paymentDao.getById(paymentId);
        GetPaymentDto result = this.modelMapperService.forDto().map(payment, GetPaymentDto.class);
        
        return new SuccessDataResult<>(result,Messages.PAYMENT_LISTED);
    }


    @Transactional
    public Result extraDaysRentCarPayment(CreateExtraPaymentRequest createExtraPaymentRequest) throws BusinessException {
    	
        double totalFee = extraDaysPaymentTotalFeeCalculator(createExtraPaymentRequest);
        checkIfPaymentDone(createExtraPaymentRequest.getCreateCreditCardDetailForPaymentRequest(), totalFee);
        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest( createExtraPaymentRequest.getRentalId(),createExtraPaymentRequest.getCustomerId(), totalFee,LocalDate.now());

        Integer invoiceId = this.invoiceService.add(createInvoiceRequest).getData();
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        Payment payment = new Payment(0, totalFee, invoice);
        this.paymentDao.save(payment);

        return new SuccessResult(Messages.PAYMENT_ADDED);
    }

    private double extraDaysPaymentTotalFeeCalculator(CreateExtraPaymentRequest createExtraPaymentRequest) throws BusinessException {
    	
        double carDailyPrice = this.carService.totalCarDailyPriceCalculator(createExtraPaymentRequest.getCarId(), createExtraPaymentRequest.getDateOfReceipt(), createExtraPaymentRequest.getCarReturnDate());
        
        List<Integer> additionalServiceIds = this.orderedAdditionalServiceService.getAdditionalServiceIdsByRentalId(createExtraPaymentRequest.getRentalId());
        double additionalDailyPrice = this.additionalServiceService.totalAdditionalServiceFeeCalculator(additionalServiceIds);
        return carDailyPrice + additionalDailyPrice;
        
    }

    @Override
    public void checkIfPaymentIdExist(int paymentId) throws BusinessException {
    	
        if (this.paymentDao.existsById(paymentId)) {
            throw new BusinessException(paymentId + Messages.PAYMENT_NOT_EXİST);
        }
        
    }

    private double totalFeeCalculator(CreatePaymentRequest createPaymentRequest) throws BusinessException {
    	
        this.customerService.checkIfCustomerIdExist(createPaymentRequest.getRentalRequest().getCustomer_UserId());
        this.carService.checkCarExist(createPaymentRequest.getRentalRequest().getCarId());

        Rental rental = this.modelMapperService.forRequest().map(createPaymentRequest.getRentalRequest(), Rental.class);
        
        double differentCityAndCarDailyPrice = this.rentalService.totalRentalDailyPriceAndDifferntCityCalculator(rental);
        double additionalTotalFee = this.additionalServiceService.totalAdditionalServiceFeeCalculator(createPaymentRequest.getAdditionalServiceList());
        return differentCityAndCarDailyPrice + additionalTotalFee;
    }

    private void checkIfPaymentDone(CreateCardDetailForPaymentRequest creditCard, double totalFee) throws BusinessException {
    	
        if (this.posService.payment(creditCard, totalFee).getMessage().isEmpty()) {
        	
            throw new BusinessException(Messages.PAYMENT_FAILED);
            
        }
        
    }

    @Transactional
    Integer PaymentSuccessor(CreatePaymentRequest createPaymentRequest, double totalFee) throws BusinessException {
    	
        CreateCardDetailForPaymentRequest creditCard = createPaymentRequest.getCreateCardDetailForPaymentRequest();
        checkIfPaymentDone(creditCard, totalFee);

        Rental rental = this.modelMapperService.forRequest().map(createPaymentRequest.getRentalRequest(), Rental.class);
        
        Integer createdRentalId = this.rentalService.addForIndividual(createPaymentRequest.getRentalRequest()).getData();
        
        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest(createdRentalId , rental.getCustomer().getUserId(),totalFee,LocalDate.now() );
        Integer invoiceId = this.invoiceService.add(createInvoiceRequest).getData();
        
        this.orderedAdditionalServiceService.addAdditionals(createdRentalId, createPaymentRequest.getAdditionalServiceList());
        	
        return invoiceId;
    }

}