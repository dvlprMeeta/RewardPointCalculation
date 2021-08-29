package com.infogain.retailer.security;

import com.infogain.retailer.entity.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

private final RetailerUserService retailerUserService;

public UserDetailsServiceImpl(RetailerUserService retailerUserService) {
        this.retailerUserService = retailerUserService;
        }

@Override
public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SecurityUser findUser = null;
        try {
        findUser = retailerUserService.findUser(userName);
        } catch (Exception e) {
        return null;
        }
        if(findUser==null) {
        return null;
        }
        List<GrantedAuthority> grants = new ArrayList<>();
        grants.add(new SimpleGrantedAuthority(findUser.getRole()));
        return new RetailerUserDetails(findUser.getUserName(), findUser.getPassword(), grants);
        }

        }
