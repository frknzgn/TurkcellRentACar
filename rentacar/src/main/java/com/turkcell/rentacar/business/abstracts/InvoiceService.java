package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.invoice.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface InvoiceService {
	
	DataResult<Integer> add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;

    Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;

    Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;

    DataResult<List<ListInvoiceDto>> getAll();

    DataResult<GetInvoiceDto> getById(int invoiceId) throws BusinessException;

    DataResult<List<ListInvoiceDto>> getByCustomerId(int customerId) throws BusinessException;

    DataResult<List<ListInvoiceDto>> getByBetweenStartingAndEndingDates(LocalDate startingDate, LocalDate endingDate) throws BusinessException;

    void checkIfInvoiceIdExists(int invoiceId) throws BusinessException;
}
