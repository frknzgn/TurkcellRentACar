package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CardDetailService;
import com.turkcell.rentacar.business.dtos.cardDetail.ListCardDetailDto;
import com.turkcell.rentacar.business.requests.carDetail.CreateCardDetailRequest;
import com.turkcell.rentacar.business.requests.carDetail.DeleteCardDetailRequest;
import com.turkcell.rentacar.business.requests.carDetail.UpdateCardDetailRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CardDetailDao;
import com.turkcell.rentacar.entites.concretes.CardDetail;

@Service
public class CardDetailManager implements CardDetailService{
	
	private CardDetailDao cardDetailDao;
	private ModelMapperService modelMapperService;
	
	public CardDetailManager(CardDetailDao cardDetailDao, ModelMapperService modelMapperService) {
		
		this.cardDetailDao = cardDetailDao;
		this.modelMapperService = modelMapperService;
		
	}
	
	@Override
	public Result add(CreateCardDetailRequest createCardDetailRequest) {
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(createCardDetailRequest, CardDetail.class);
		this.cardDetailDao.save(cardDetail);
		return new SuccessResult(Messages.CARD_ADDED);
		
	}
	

	@Override
	public DataResult<List<ListCardDetailDto>> getall() {
		
		var result = this.cardDetailDao.findAll();
		
		List<ListCardDetailDto> response = result.stream().
				map(cardDetail->this.modelMapperService.forDto().
						map(cardDetail, ListCardDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCardDetailDto>>(response, Messages.CARD_DETAİLS_GET);
		
	}

	@Override
	public DataResult<List<ListCardDetailDto>> getAllByCustomerId(int customerId) {
		
		var result = this.cardDetailDao.getByCustomer_CustomerId(customerId);
		List<ListCardDetailDto> response = result.stream().
				map(carDetail-> this.modelMapperService.forDto().
						map(carDetail, ListCardDetailDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCardDetailDto>>(response, Messages.CARD_DETAİL_GETBY_CUSTOMERID);
		
	}
	

	@Override
	public Result update(UpdateCardDetailRequest updateCardDetailRequest) {
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(updateCardDetailRequest, CardDetail.class);
		this.cardDetailDao.save(cardDetail);
		
		return new SuccessResult(Messages.CARD_DETAİLS_UPDATED);
		
	}

	@Override
	public Result delete(DeleteCardDetailRequest deleteCardDetailRequest) {
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(deleteCardDetailRequest, CardDetail.class);
		this.cardDetailDao.delete(cardDetail);
		
		return new SuccessResult(Messages.CARD_DETAİL_DELETED);
		
	}
	
	
}
