package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.UpdateInvoiceRequest;
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
	
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService) {
		
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		
	}
	
	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult("Invoice.Added.");
		
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getall() {
		
		var result = this.invoiceDao.findAll();
		List<ListInvoiceDto> response = result.stream().
						map(invoice-> this.modelMapperService.forDto().
								map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response,"Invoice.Listed");
	}

	@Override
	public DataResult<List<ListInvoiceDto>> getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate returnDate,LocalDate rentDate) {
		
		var result = this.invoiceDao.findAllByRentDateLessThanEqualAndReturnDateGreaterThanEqual(returnDate, rentDate);
		List<ListInvoiceDto> response = result.stream()
							.map(invoice->this.modelMapperService.forDto().
									map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceDto>>(response,"Invoice.ListedByDate");
		
	}

//	@Override
//	public DataResult<List<ListInvoiceDto>> getByCustomers_userId(int userId) {
//
//		var result = this.invoiceDao.findByUser_userId(userId);
//		List<ListInvoiceDto> response = result.stream().
//							map(invoice-> this.modelMapperService.forDto().
//									map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());
//		return new SuccessDataResult<List<ListInvoiceDto>>(response, "Invoice.ListedByCustomerId");
//		
//	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		
		this.invoiceDao.save(invoice);
		
		return new SuccessResult("Invoice.Updated");
		
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		
		Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
		
		this.invoiceDao.delete(invoice);
		
		return new SuccessResult("Invoice.Deleted");
	}

}
