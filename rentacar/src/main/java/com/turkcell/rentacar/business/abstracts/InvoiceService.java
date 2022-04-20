package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.invoice.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface InvoiceService {
	
	DataResult<Integer> add(CreateInvoiceRequest createInvoiceRequest);
	
	DataResult<List<ListInvoiceDto>> getAll();
    DataResult<GetInvoiceDto> getById(int invoiceId);
    DataResult<List<ListInvoiceDto>> getByCustomerId(int customerId);
    DataResult<List<ListInvoiceDto>> getByBetweenStartingAndEndingDates(LocalDate startingDate, LocalDate endingDate);
    
    Result update(UpdateInvoiceRequest updateInvoiceRequest);

    Result delete(DeleteInvoiceRequest deleteInvoiceRequest);

    void checkIfInvoiceIdExists(int invoiceId);
}
