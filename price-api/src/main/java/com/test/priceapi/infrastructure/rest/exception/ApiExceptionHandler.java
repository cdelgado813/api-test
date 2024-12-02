package com.test.priceapi.infrastructure.rest.exception;

import com.test.priceapi.domain.exception.PriceNotFoundException;
import com.test.priceapi.infrastructure.exception.InfrastructureException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePriceNotFoundException(PriceNotFoundException ex) {
        return new ErrorResponse("PRICE_NOT_FOUND", "No price was not found");
    }

    @ExceptionHandler(InfrastructureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInfrastructureException(InfrastructureException ex) {
        return new ErrorResponse("ERROR_ACCESSING_DBS", "Something went wrong trying to connect with dbs");
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex) {
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "Something went wrong");
    }
}