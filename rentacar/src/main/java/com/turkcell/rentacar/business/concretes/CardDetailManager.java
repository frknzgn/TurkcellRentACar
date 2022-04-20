package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CardDetailService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.cardDetail.ListCardDetailDto;
import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailRequest;
import com.turkcell.rentacar.business.requests.cardDetail.DeleteCardDetailRequest;
import com.turkcell.rentacar.business.requests.cardDetail.UpdateCardDetailRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
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
	private CustomerService customerService;
	
	public CardDetailManager(CardDetailDao cardDetailDao, ModelMapperService modelMapperService, @Lazy CustomerService customerService) {
		
		this.cardDetailDao = cardDetailDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		
	}
	
	@Override
	public Result add(CreateCardDetailRequest createCardDetailRequest) {
		
		System.out.println(createCardDetailRequest);
		this.customerService.checkIfCustomerIdExist(createCardDetailRequest.getCustomerId());
		checkIfCardDetailNotExist(createCardDetailRequest.getCardNo());
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(createCardDetailRequest, CardDetail.class);
		
		cardDetail.setCardDetailId(0);
		cardDetail.setCustomer(this.customerService.getById(createCardDetailRequest.getCustomerId())); 
		this.cardDetailDao.save(cardDetail);
		
		return new SuccessResult(Messages.CARD_ADDED);
		
	}
	

	private void checkIfCardDetailNotExist(String cardNo) {
		
		if(this.cardDetailDao.existsByCardNo(cardNo)) {
			
			throw new BusinessException(Messages.CARD_EXİST);
		}
		
	}

	@Override
	public DataResult<List<ListCardDetailDto>> getall() {
		
		var result = this.cardDetailDao.findAll();
		
		List<ListCardDetailDto> response = result.stream().
				map(cardDetail->this.modelMapperService.forDto().
						map(cardDetail, ListCardDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCardDetailDto>>(response, Messages.CARD_DETAILS_GET);
		
	}
	
	@Override
	public Result update(UpdateCardDetailRequest updateCardDetailRequest) {
		
		checkIfCardByCardIdExist(updateCardDetailRequest.getCardDetailId());
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(updateCardDetailRequest, CardDetail.class);
		cardDetail.setCustomer(this.customerService.getById(updateCardDetailRequest.getCustomerId()));
		
		this.cardDetailDao.save(cardDetail);
		
		return new SuccessResult(Messages.CARD_DETAILS_UPDATED);
		
	}

	private void checkIfCardByCardIdExist(int cardDetailId) {
		
		if (!this.cardDetailDao.existsById(cardDetailId)) {
            throw new BusinessException(Messages.CARD_NOT_FOUND);
        }
		
	}

	@Override
	public Result delete(DeleteCardDetailRequest deleteCardDetailRequest) {
		
		checkIfCardByCardIdExist(deleteCardDetailRequest.getCarDetailId());
		
		CardDetail cardDetail = this.modelMapperService.forRequest().map(deleteCardDetailRequest, CardDetail.class);
		this.cardDetailDao.delete(cardDetail);
		
		return new SuccessResult(Messages.CARD_DETAIL_DELETED);
		
	}
	
	
}
