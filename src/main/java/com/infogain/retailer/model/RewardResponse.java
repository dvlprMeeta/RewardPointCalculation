package com.infogain.retailer.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardResponse {

    private long customerId;
    private String customerName;
    private long lastMonthRewardPoints;
    private long SecondMonthRewardPoints;
    private long ThirdMonthRewardPoints;
    private long totalRewards;
}
