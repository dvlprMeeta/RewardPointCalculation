package com.infogain.retailer.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardTransactionList {

    private Long rewardPoint;
    private String customerName;
    private double amount;
    private Timestamp transactionDate;


}
