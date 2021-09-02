package com.infogain.retailer.controller;

import com.infogain.retailer.model.RewardResponse;
import com.infogain.retailer.model.RewardTransactionResponse;
import com.infogain.retailer.service.RewardServiceI;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@WithMockUser(authorities = "ROLE_ADMIN")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RewardControllerTest {


    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @Mock
    private RewardServiceI rewardsService;


    private List<RewardResponse> rewardResponseList;
    private Set<RewardTransactionResponse> rewardTransactionResponseList;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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
    }


    @Test
    void testGetTransactionDetailsByName() {
        try {
            when(rewardsService.getTransactionDetailsByName()).thenReturn(rewardResponseList);
            mockMvc.perform(get("/rewards/v1/getRewardsDetailsByCustomer")).
                    andExpect(status().isOk()).
                    andExpect((jsonPath("$.*", Matchers.hasSize(3)))).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }


    @Test
    void testGetTotalRewardsByMonth() {
        try {
            when(rewardsService.getTotalRewardsByMonth()).thenReturn(rewardTransactionResponseList);
            mockMvc.perform(get("/rewards/v1/getRewardsDetailsByCustomerAndMonth")).
                    andExpect(status().isOk()).
                    andExpect((jsonPath("$.*", Matchers.hasSize(3)))).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }


}
