package br.com.asyncclient.web;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonTypeName(value = "error_message")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Builder
@Getter
@ToString()
public class ErrorMessage {

    private final HttpStatus code;

    private final List<String> messages;

}