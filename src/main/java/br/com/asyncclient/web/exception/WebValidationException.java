package br.com.asyncclient.web.exception;

import lombok.Getter;

import java.util.List;

public class WebValidationException extends RuntimeException {

    private static final long serialVersionUID = 9200295698741813755L;

    @Getter
    private final List<String> errorValidationMessages;

    public WebValidationException(List<String> errorValidationMessages) {
        this.errorValidationMessages = errorValidationMessages;
    }

}
