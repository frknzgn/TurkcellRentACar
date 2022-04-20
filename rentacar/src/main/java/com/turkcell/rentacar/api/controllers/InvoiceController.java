package com.turkcell.rentacar.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.invoice.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
	
	private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    
    Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
        return this.invoiceService.add(createInvoiceRequest);
    }

    @PutMapping("/update")
    Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
        return this.invoiceService.update(updateInvoiceRequest);
    }

    @DeleteMapping("/delete")
    Result delete( DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
        return this.invoiceService.delete(deleteInvoiceRequest);
    }

    @GetMapping("/getAll")
    DataResult<List<ListInvoiceDto>> getAll() {
        return this.invoiceService.getAll();
    }

    @GetMapping("/getById")
    DataResult<GetInvoiceDto> getById(int invoiceId) throws BusinessException {
        return this.invoiceService.getById(invoiceId);
    }

    @GetMapping("/getByCustomerId")
    DataResult<List<ListInvoiceDto>> getByCustomerId(@RequestParam int customerId) throws BusinessException {
        return this.invoiceService.getByCustomerId(customerId);
    }

    @GetMapping("/getByStartingAndEndingDate")
    DataResult<List<ListInvoiceDto>> getByBetweenStartingAndEndingDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startingDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endingDate) throws BusinessException {
        return this.invoiceService.getByBetweenStartingAndEndingDates(startingDate, endingDate);
    }

	

}
