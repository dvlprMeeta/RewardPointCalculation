package com.infogain.retailer.service;

import com.infogain.retailer.model.RewardResponse;
import com.infogain.retailer.model.RewardTransactionResponse;

import java.util.List;
import java.util.Set;

public interface RewardServiceI {

     List<RewardResponse> getTransactionDetailsByName();

     Set<RewardTransactionResponse> getTotalRewardsByMonth();
}
