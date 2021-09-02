package com.infogain.retailer.repository;


import com.infogain.retailer.entity.RewardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RewardTransactionRepository extends JpaRepository<RewardTransaction,Long> {

     List<RewardTransaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from, Timestamp to);


     List<RewardTransaction> findAllByCustomerId(Long customerId);

}
