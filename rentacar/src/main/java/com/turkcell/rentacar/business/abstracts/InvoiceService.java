package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface InvoiceService {
	
	Result add(CreateInvoiceRequest createInvoiceRequest);
	
	DataResult<List<ListInvoiceDto>> getall();
	DataResult<List<ListInvoiceDto>> getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate returnDate,LocalDate rentDate);
	//DataResult<List<ListInvoiceDto>> getByCustomers_userId(int userId);
	
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
}
