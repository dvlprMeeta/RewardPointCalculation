package com.infogain.retailer.security;

import com.infogain.retailer.entity.SecurityUser;
import com.infogain.retailer.exception.UserNameNotFoundException;
import com.infogain.retailer.repository.RetailerSecurityUserServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetailerUserServiceImpl  implements RetailerUserService{

    RetailerSecurityUserServiceRepository retailerSecurityUserServiceRepository;

    public RetailerUserServiceImpl(RetailerSecurityUserServiceRepository retailerSecurityUserServiceRepository) {
        this.retailerSecurityUserServiceRepository = retailerSecurityUserServiceRepository;
    }

    @Override
    public SecurityUser findUser(String userId) throws UserNameNotFoundException {
        Optional<SecurityUser> findByUserName = retailerSecurityUserServiceRepository.findByUserName(userId);
        return findByUserName.orElse(null);
    }
}
