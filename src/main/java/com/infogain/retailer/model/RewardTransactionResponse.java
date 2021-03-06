package com.infogain.retailer.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardTransactionResponse {


    private String customerName;
    private String month;
    private Integer noOfTransaction;
    private Long totalRewardPoint;
    private Double totalAMount;

    private List<RewardTransactionList> rewardTransactionListList;



}
