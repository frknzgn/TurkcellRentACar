package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.invoice.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentacar.entites.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {
	
	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private RentalService rentalService;
	
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService, CustomerService customerService,RentalService rentalService) {
		
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.rentalService = rentalService;
		
		
	}
	
	@Override
	public DataResult<Integer> add(CreateInvoiceRequest createInvoiceRequest) {
		
		this.customerService.checkIfCustomerIdExist(createInvoiceRequest.getCustomerId());
	    this.rentalService.checkIfRentalExists(createInvoiceRequest.getRentalId());
	    
	    Invoice response = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
	
	    response.setInvoiceId(0);
		this.invoiceDao.save(response);
		
		return new SuccessDataResult<Integer>(Messages.INVOICE_ADDED);
		
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAll() {
		
		var result = this.invoiceDao.findAll();
		List<ListInvoiceDto> response = result.stream().
						map(invoice-> this.modelMapperService.forDto().
								map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response,"Invoice.Listed");
	}


	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		
		this.customerService.checkIfCustomerIdExist(updateInvoiceRequest.getCustomerId());
        this.rentalService.checkIfRentalExists(updateInvoiceRequest.getRentalId());
        checkIfInvoiceIdExists(updateInvoiceRequest.getInvoiceId());

        Invoice response = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
        this.invoiceDao.save(response);
        return new SuccessResult(Messages.INVOICE_UPDATED);
		
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		
		 checkIfInvoiceIdExists(deleteInvoiceRequest.getInvoiceId());

	     Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
	     this.invoiceDao.deleteById(invoice.getInvoiceId());
	     
	     return new SuccessResult(Messages.INVOICE_DELETED);
	     
	}

	@Override
	public DataResult<GetInvoiceDto> getById(int invoiceId) throws BusinessException {
		
		checkIfInvoiceIdExists(invoiceId);

        Invoice response = this.invoiceDao.getById(invoiceId);
        GetInvoiceDto result = this.modelMapperService.forDto().map(response, GetInvoiceDto.class);

        return new SuccessDataResult<>(result,Messages.INVOICE_LISTED);
        
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getByCustomerId(int customerId) throws BusinessException {
		
		checkIfCustomerIdExists(customerId);
        this.customerService.checkIfCustomerIdExist(customerId);

        List<Invoice> response = this.invoiceDao.getByCustomerUserId(customerId);
        List<ListInvoiceDto> result = response.stream()
                .map(invoice -> this.modelMapperService.forDto().
                		map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
        
        return new SuccessDataResult<>(result, Messages.INVOICE_LISTED);
		
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getByBetweenStartingAndEndingDates(LocalDate startingDate,LocalDate endingDate) throws BusinessException {
		
		checkIfTodayDateWithDateByDate(startingDate);
        checkIfTodayDateWithDateByDate(endingDate);
        checkIfStatingDateLessThanEndingDate(startingDate, endingDate);

        List<Invoice> response = this.invoiceDao.getByCreatingDateBetween(startingDate, endingDate);
        List<ListInvoiceDto> result = response.stream()
                .map(invoice -> this.modelMapperService.forDto().
                		map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
        
        return new SuccessDataResult<>(result, Messages.INVOICE_LISTED);
		
	}
	
	  private void checkIfTodayDateWithDateByDate(LocalDate date) throws BusinessException {
	        LocalDate today = LocalDate.now();
	        if (today.isBefore(date)) {
	            throw new BusinessException(Messages.INVOICE_CANNOT_SEARCH_FOR_THE_NEXT_DAYS);
	        }
	    }

	  private void checkIfStatingDateLessThanEndingDate(LocalDate startingDate, LocalDate endingDate) throws BusinessException {
	        if (startingDate.isAfter(endingDate)) {
	            throw new BusinessException(Messages.INVOICE_STARTING_DATE_CANNOT_GREATER_THAN_END_DATE);
	        }
	    }
	  
	@Override
	public void checkIfInvoiceIdExists(int invoiceId) throws BusinessException {
		
		if (!this.invoiceDao.existsById(invoiceId)) {
            throw new BusinessException(Messages.INVOICE_NOT_EXIST);
        }
		
	}
	
	private void checkIfCustomerIdExists(int customerId) throws BusinessException {
		
        if (!this.invoiceDao.existsByCustomer_UserId(customerId)) {
        	
            throw new BusinessException(Messages.CUSTOMER_NOT_EXİST);
            
        }
    }
	
	

}
