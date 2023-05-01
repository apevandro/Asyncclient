package br.com.asyncclient.web;

import br.com.asyncclient.web.exception.WebValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

import java.util.Collections;

@RestControllerAdvice
public class Advice {

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public static ErrorMessage handleServerWebInputException() {
        String message = "Input with invalid field - Decoding Exception";
        return ErrorMessage.builder()
                .code(HttpStatus.BAD_REQUEST)
                .messages(Collections.singletonList(message))
                .build();
    }

    @ExceptionHandler(WebValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public static ErrorMessage handleWebValidationException(WebValidationException validationException) {
        return ErrorMessage.builder()
                .code(HttpStatus.BAD_REQUEST)
                .messages(validationException.getErrorValidationMessages())
                .build();
    }

}