package com.infogain.retailer.util;


import com.infogain.retailer.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {


    @Lookup
    public ResponseDTO getResponseDto() {
        return null;
    }

    public ResponseDTO createResponseDto(String responseDesc, int responseCode, Object object) {
        ResponseDTO responseDto = getResponseDto();
        responseDto.setObject(object);
        responseDto.setResponseCode(responseCode);
        responseDto.setResponseStatus(responseDesc);
        return responseDto;
    }
}
