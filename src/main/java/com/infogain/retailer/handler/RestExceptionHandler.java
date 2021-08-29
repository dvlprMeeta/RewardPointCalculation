package com.infogain.retailer.handler;

import com.infogain.retailer.dto.ResponseDTO;
import com.infogain.retailer.exception.UserNameNotFoundException;
import com.infogain.retailer.util.ResponseUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.NamingSecurityException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    private final ResponseUtil responseUtil;

    int badREQUEST = 400;

    public RestExceptionHandler(ResponseUtil responseUtil) {
        this.responseUtil = responseUtil;
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    protected ResponseDTO handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        String error = ex.getParameterName() + " parameter is missing";
        ApiError  apiError=new ApiError(BAD_REQUEST, error, ex);
        apiError.setResponseCode(badREQUEST);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    protected ResponseDTO handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(
                new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseDTO handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseBody
    protected ResponseDTO handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseBody
    protected ResponseDTO handleConstraintViolation(UserNameNotFoundException ex) {
        ApiError apiError = new ApiError(FORBIDDEN);
        apiError.setMessage("Failed Authorization");
        apiError.addValidationError("Authrization", "UserName/Password", "Not Authorized", ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NamingSecurityException.class)
    @ResponseBody
    protected ResponseDTO handleConstraintViolation(NamingSecurityException ex) {
        ApiError apiError = new ApiError(FORBIDDEN);
        apiError.setMessage("Failed Authorization");
        apiError.addValidationError("Authrization", "UserName/Password", "Not Authorized", ex.getMessage());
        return buildResponseEntity(apiError);
    }


    private ResponseDTO buildResponseEntity(ApiError apiError) {
        return responseUtil.createResponseDto(apiError.getMessage(), apiError.getStatusCode(), apiError);
    }

}