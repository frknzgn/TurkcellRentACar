package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.cardDetail.ListCardDetailDto;
import com.turkcell.rentacar.business.requests.carDetail.CreateCardDetailRequest;
import com.turkcell.rentacar.business.requests.carDetail.DeleteCardDetailRequest;
import com.turkcell.rentacar.business.requests.carDetail.UpdateCardDetailRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CardDetailService {
	
	Result add(CreateCardDetailRequest createCardDetailRequest);
	
	DataResult<List<ListCardDetailDto>> getall();
	DataResult<List<ListCardDetailDto>> getAllByCustomerId(int customerId);
	
	Result update(UpdateCardDetailRequest updateCardDetailRequest);
	
	Result delete(DeleteCardDetailRequest deleteCardDetailRequest);
}
