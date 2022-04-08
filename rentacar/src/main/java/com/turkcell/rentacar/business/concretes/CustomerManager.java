package com.turkcell.rentacar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerDao;

@Service
public class CustomerManager implements CustomerService {
	
    private CustomerDao customerDao;

    @Autowired
    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void checkIfCustomerIdExist(int customerId) throws BusinessException {
    	
        if (!this.customerDao.existsById(customerId)) {
        	
            throw new BusinessException(Messages.CUSTOMER_NOT_EXİST);
            
        }
    }

	@Override
	public void checkIfEmailExist(String email) {
		
		if(!this.customerDao.existsByEmail(email)) {
			
			throw new BusinessException(Messages.EMAIL_EXIST);
			
		}		
	}
    
    
}