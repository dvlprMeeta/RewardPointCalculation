package com.infogain.retailer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardResponse {

    private long customerId;
    private String customerName;
    private long lastMonthRewardPoints;
    private long secondMonthRewardPoints;
    private long thirdMonthRewardPoints;
    private long totalRewards;
}
