package com.turkcell.rentacar.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.invoice.ListInvoiceDto;
import com.turkcell.rentacar.business.requests.invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.invoice.UpdateInvoiceRequest;
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
	
	@PostMapping("/add")
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		
		return this.invoiceService.add(createInvoiceRequest);
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListInvoiceDto>> getall(){
		
		return this.invoiceService.getall();
		
	}
	
	@GetMapping("/getbydate")
	public DataResult<List<ListInvoiceDto>> getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(@RequestParam LocalDate returnDate,@RequestParam LocalDate rentDate){
		
		return this.invoiceService.getAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(returnDate, rentDate);
		
	}
	
//	@GetMapping("/getbycustomerid")
//	public DataResult<List<ListInvoiceDto>> getByCustomers_userId(@RequestParam int userId){
//		
//		return this.invoiceService.getByCustomers_userId(userId);
//		
//	}
	
	@PutMapping("/update")
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		
		return this.invoiceService.update(updateInvoiceRequest);
		
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		
		return this.invoiceService.delete(deleteInvoiceRequest);
		
	}

}
