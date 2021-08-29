package com.infogain.retailer.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication auth)  {
        RetailerUserDetails retailerUserDetails;
        if (!validateUser(auth)) {
            throw new BadCredentialsException("UserName/Password Is Invalid");
        }
        retailerUserDetails = (RetailerUserDetails) userDetailsService.loadUserByUsername(auth.getName().toUpperCase());
        if(!auth.getCredentials().toString().equalsIgnoreCase(retailerUserDetails.getPassword())) {
            throw new BadCredentialsException("Password Is Invalid");
        }
        return new UsernamePasswordAuthenticationToken(retailerUserDetails, null, retailerUserDetails.getAuthorities());

    }

    public boolean validateUser(Authentication authentication) {
        try {
            String userName = authentication.getName();
            String password = authentication.getCredentials().toString();
            return (StringUtils.isNoneBlank(userName) && StringUtils.isNoneBlank(password));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
    }

}