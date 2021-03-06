package com.infogain.retailer.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class RetailerUserDetails extends User {

        private static final long serialVersionUID = 7643377486418587906L;

        public RetailerUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }
    }

