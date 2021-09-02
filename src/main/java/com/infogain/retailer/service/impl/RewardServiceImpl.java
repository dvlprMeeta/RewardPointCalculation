package com.infogain.retailer.service.impl;


import com.infogain.retailer.constants.RetailerConstants;
import com.infogain.retailer.entity.Customer;
import com.infogain.retailer.entity.RewardTransaction;
import com.infogain.retailer.model.RewardResponse;
import com.infogain.retailer.model.RewardTransactionList;
import com.infogain.retailer.model.RewardTransactionResponse;
import com.infogain.retailer.repository.CustomerRepository;
import com.infogain.retailer.repository.RewardTransactionRepository;
import com.infogain.retailer.service.RewardServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RewardServiceImpl implements RewardServiceI {

    private final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMMM");

    @Autowired
    RewardTransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<RewardResponse> getTransactionDetailsByName() {

        List<Customer> findALlCustomer = customerRepository.findAll();
        List<RewardResponse> rewardResponseList = new ArrayList<>();
        findALlCustomer.forEach(customer -> {
            Timestamp previousMonthTimestamp = getDateBasedOnOffSetDays(RetailerConstants.DAYS_IN_MONTH);
            Timestamp secondPreviousMonthTimestamp = getDateBasedOnOffSetDays(2*RetailerConstants.DAYS_IN_MONTH);
            Timestamp thirdPreviousMonthTimestamp = getDateBasedOnOffSetDays(3*RetailerConstants.DAYS_IN_MONTH);

            List<RewardTransaction> previousMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customer.getCustomerId(),previousMonthTimestamp, Timestamp.from(Instant.now()));
            List<RewardTransaction> secondPreviousMonthTransactions = transactionRepository
                    .findAllByCustomerIdAndTransactionDateBetween(customer.getCustomerId(),secondPreviousMonthTimestamp, previousMonthTimestamp);
            List<RewardTransaction> thirdPreviousMonthTransactions = transactionRepository
                    .findAllByCustomerIdAndTransactionDateBetween(customer.getCustomerId(),thirdPreviousMonthTimestamp, secondPreviousMonthTimestamp);

            Long lastMonthRewardPoints = getRewardsPerMonth(previousMonthTransactions);
            Long lastSecondMonthRewardPoints = getRewardsPerMonth(secondPreviousMonthTransactions);
            Long lastThirdMonthRewardPoints = getRewardsPerMonth(thirdPreviousMonthTransactions);

            RewardResponse rewardResponse = new RewardResponse();
            rewardResponse.setCustomerId(customer.getCustomerId());
            rewardResponse.setCustomerName(customer.getCustomerName());
            rewardResponse.setLastMonthRewardPoints(lastMonthRewardPoints);
            rewardResponse.setSecondMonthRewardPoints(lastSecondMonthRewardPoints);
            rewardResponse.setThirdMonthRewardPoints(lastThirdMonthRewardPoints);
            rewardResponse.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

            rewardResponseList.add(rewardResponse);
        });
        return rewardResponseList;
    }

    @Override
    public Set<RewardTransactionResponse> getTotalRewardsByMonth() {
        List<Customer> findAllCustomer = customerRepository.findAll();
        Set<RewardTransactionResponse> rewardTransactionResponseList = new HashSet<>();
        findAllCustomer.forEach(customer -> {
            try {
                List<RewardTransaction> rewardTransaction = transactionRepository.
                        findAllByCustomerId(customer.getCustomerId());
                Map<String,List<RewardTransaction>>  transactionAmountMap = rewardTransaction.stream().
                        collect(Collectors.groupingBy(reward -> timestampToMonth(reward.getTransactionDate())));
                rewardTransaction.forEach(reward ->  constructRewardTransactionResponse(rewardTransactionResponseList, customer, transactionAmountMap, reward));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return rewardTransactionResponseList;
    }

    private void constructRewardTransactionResponse(Set<RewardTransactionResponse> rewardTransactionResponseList, Customer customer, Map<String, List<RewardTransaction>> transactionAmountMap, RewardTransaction reward) {
        List<RewardTransaction> rewardTransactions = transactionAmountMap.get(timestampToMonth(reward.getTransactionDate()));
        List<RewardTransactionList> rewardTransactionListData = new ArrayList<>();
        rewardTransactions.forEach(rewards -> getRewardTransactionList(customer, rewardTransactionListData, rewards));
        creeateRewardTransactionResponse(rewardTransactionResponseList, customer, reward, rewardTransactions, rewardTransactionListData);
    }

    private void creeateRewardTransactionResponse(Set<RewardTransactionResponse> rewardTransactionResponseList, Customer customer, RewardTransaction reward, List<RewardTransaction> rewardTransactions, List<RewardTransactionList> rewardTransactionListData) {
        RewardTransactionResponse rewardTransactionResponse= RewardTransactionResponse.builder().
                customerName(customer.getCustomerName()).
                noOfTransaction(rewardTransactions.size()).
                totalRewardPoint(getRewardsPerMonth(rewardTransactions)).
                month(timestampToMonth(reward.getTransactionDate())).
                totalAMount(rewardTransactions.stream().mapToDouble(RewardTransaction::getTransactionAmount).sum()).
                rewardTransactionListList(rewardTransactionListData).build();
        rewardTransactionResponseList.add(rewardTransactionResponse);
    }

    private void getRewardTransactionList(Customer customer, List<RewardTransactionList> rewardTransactionListData, RewardTransaction rewards) {
        RewardTransactionList rewardTransactionList = new RewardTransactionList();
        rewardTransactionList.setTransactionDate(rewards.getTransactionDate());
        rewardTransactionList.setAmount(rewards.getTransactionAmount());
        rewardTransactionList.setRewardPoint(calculateRewards(rewards));
        rewardTransactionList.setCustomerName(customer.getCustomerName());
        rewardTransactionListData.add(rewardTransactionList);
    }

    private Long getRewardsPerMonth(List<RewardTransaction> transactions) {
        return transactions.stream().map(this::calculateRewards).mapToLong(r -> r).sum();
    }

    private Long calculateRewards(RewardTransaction t) {
        if (t.getTransactionAmount() > RetailerConstants.FIRST_REWARD_LIMIT && t.getTransactionAmount() <= RetailerConstants.SECOND_REWARD_LIMIT) {
            return Math.round(t.getTransactionAmount() - RetailerConstants.FIRST_REWARD_LIMIT);
        } else if (t.getTransactionAmount() > RetailerConstants.SECOND_REWARD_LIMIT) {
            return Math.round(t.getTransactionAmount() - RetailerConstants.SECOND_REWARD_LIMIT) * 2
                    + (RetailerConstants.SECOND_REWARD_LIMIT - RetailerConstants.FIRST_REWARD_LIMIT);
        } else
            return 0L;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }


    public String timestampToMonth(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return monthFormat.format(timestamp);
        }
    }
}
