package com.infogain.retailer.security;

import com.infogain.retailer.entity.SecurityUser;
import com.infogain.retailer.exception.UserNameNotFoundException;

public interface RetailerUserService {
    SecurityUser findUser(String userId) throws  UserNameNotFoundException;
}
