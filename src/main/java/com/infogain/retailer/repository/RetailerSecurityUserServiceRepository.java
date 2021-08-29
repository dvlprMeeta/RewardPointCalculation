package com.infogain.retailer.repository;

import com.infogain.retailer.entity.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetailerSecurityUserServiceRepository extends JpaRepository<SecurityUser, Integer> {
    public Optional<SecurityUser> findByUserName(String username);
}
