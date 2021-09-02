package com.infogain.retailer.controller;

import com.infogain.retailer.dto.ResponseDTO;
import com.infogain.retailer.model.RewardResponse;
import com.infogain.retailer.service.RewardServiceI;
import com.infogain.retailer.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
public class RewardController {


        private final RewardServiceI rewardsService;
        private final ResponseUtil responseUtil;

        public RewardController(RewardServiceI rewardsService, ResponseUtil responseUtil) {
            this.rewardsService = rewardsService;
            this.responseUtil = responseUtil;
        }

        @ApiOperation(value="Total Rewards By Customer Name")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
        @GetMapping(value = "/v1/getRewardsDetailsByCustomer",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseDTO<RewardResponse> getTransactionDetailsByName() {
            return responseUtil.createResponseDto("Total Rewards By Customer Name",200,rewardsService.getTransactionDetailsByName());
        }



        @ApiOperation(value="Total Rewards Details")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
        @GetMapping(value = "/v1/getRewardsDetailsByCustomerAndMonth",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseDTO getTotalRewardsByMonth() {
            return responseUtil.createResponseDto("Total Rewards By Customer Name",200,rewardsService.getTotalRewardsByMonth());
        }
}
