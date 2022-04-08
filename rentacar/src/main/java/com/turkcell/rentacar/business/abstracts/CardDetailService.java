package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.cardDetail.ListCardDetailDto;
import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailRequest;
import com.turkcell.rentacar.business.requests.cardDetail.DeleteCardDetailRequest;
import com.turkcell.rentacar.business.requests.cardDetail.UpdateCardDetailRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CardDetailService {
	
	Result add(CreateCardDetailRequest createCardDetailRequest);
	
	DataResult<List<ListCardDetailDto>> getall();
	
	Result update(UpdateCardDetailRequest updateCardDetailRequest);
	
	Result delete(DeleteCardDetailRequest deleteCardDetailRequest);
}
