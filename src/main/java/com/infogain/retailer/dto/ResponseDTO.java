package com.infogain.retailer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@Scope(value="prototype")
public class ResponseDTO {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String responseStatus;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int responseCode;

    private Object object;

}
