package com.infogain.retailer.service;

import com.infogain.retailer.entity.Customer;
import com.infogain.retailer.entity.RewardTransaction;
import com.infogain.retailer.model.RewardResponse;
import com.infogain.retailer.model.RewardTransactionResponse;
import com.infogain.retailer.repository.CustomerRepository;
import com.infogain.retailer.repository.RewardTransactionRepository;
import com.infogain.retailer.service.impl.RewardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RewardServiceTest {


    @InjectMocks
    RewardServiceImpl rewardServiceImpl;

    @Mock
    RewardServiceImpl rewardServiceMock;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    RewardTransactionRepository transactionRepository;

    private List<RewardResponse> rewardResponseList;
    private Set<RewardTransactionResponse> rewardTransactionResponseList;
    private List<Customer> customerList;
    private List<RewardTransaction> rewardTransactionList;



    @Before
    public void setUp() throws Exception {

        rewardResponseList = new ArrayList<>();
        RewardResponse rewardResponse = new RewardResponse();
        rewardResponse.setCustomerName("Meeta");
        rewardResponse.setSecondMonthRewardPoints(300L);
        rewardResponse.setSecondMonthRewardPoints(200L);
        rewardResponse.setLastMonthRewardPoints(100L);
        rewardResponse.setTotalRewards(600L);
        rewardResponse.setCustomerId(101L);


        RewardResponse rewardResponse1 = new RewardResponse();
        rewardResponse1.setCustomerName("XYZ");
        rewardResponse1.setSecondMonthRewardPoints(300L);
        rewardResponse1.setSecondMonthRewardPoints(200L);
        rewardResponse1.setLastMonthRewardPoints(100L);
        rewardResponse1.setTotalRewards(600L);
        rewardResponse1.setCustomerId(101L);
        rewardResponseList.add(rewardResponse);
        rewardResponseList.add(rewardResponse1);

        rewardTransactionResponseList = new HashSet<>();

        RewardTransactionResponse rewardTransactionResponse = new RewardTransactionResponse();
        rewardTransactionResponse.setNoOfTransaction(3);
        rewardTransactionResponse.setMonth("MAY");
        rewardTransactionResponse.setTotalRewardPoint(300L);
        rewardTransactionResponse.setCustomerName("Meeta");
        rewardTransactionResponse.setTotalAMount(1234d);

        rewardTransactionResponseList.add(rewardTransactionResponse);

        customerList = new ArrayList<>();

        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("Meeta");

        customerList.add(customer);


        rewardTransactionList = new ArrayList<>();

        RewardTransaction rewardTransaction = new RewardTransaction();
        rewardTransaction.setTransactionDate(new Timestamp(new Date().getTime()));
        rewardTransaction.setCustomerId(123L);
        rewardTransaction.setTransactionAmount(1000d);
        rewardTransaction.setTransactionId(1001L);
        rewardTransactionList.add(rewardTransaction);
    }


    @Test
    public void testGetTransactionDetailsByName() {
        when(customerRepository.findAll()).thenReturn(customerList);
        when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(anyLong(),any(),any())).thenReturn(rewardTransactionList);
        List<RewardResponse> transactionDetailsByName = rewardServiceImpl.getTransactionDetailsByName();
        verify(customerRepository, Mockito.times(1)).findAll();
        verify(transactionRepository, Mockito.times(3)).findAllByCustomerIdAndTransactionDateBetween(anyLong(),any(),any());
        RewardResponse actualRewardResponse=  RewardResponse.builder().
                customerId(123L).
                customerName("Meeta").
                thirdMonthRewardPoints(1850L).
                secondMonthRewardPoints(1850L).
                lastMonthRewardPoints(1850L).
                totalRewards(5550L).build();
        List<RewardResponse> actualRewardResponseList = new ArrayList<>();
        actualRewardResponseList.add(actualRewardResponse);
        assertEquals("Actual Reward Transaction and Expected Reward Transaction Doesn't Match",transactionDetailsByName,actualRewardResponseList);
    }

    @Test
    public void testGetTotalRewardsByMonth() {
        when(customerRepository.findAll()).thenReturn(customerList);
        when(transactionRepository.findAllByCustomerId(any())).thenReturn(rewardTransactionList);
        Set<RewardTransactionResponse> transactionDetailsByMonth = rewardServiceImpl.getTotalRewardsByMonth();
        verify(customerRepository, Mockito.times(1)).findAll();
        verify(transactionRepository, Mockito.times(1)).findAllByCustomerId(any());
        assertNotNull("Actual Reward Transaction and Expected Reward Transaction Doesn't Match",transactionDetailsByMonth);
    }
}
