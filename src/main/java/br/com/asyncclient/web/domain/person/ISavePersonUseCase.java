package br.com.asyncclient.web.domain.person;

import br.com.asyncclient.web.domain.person.constraint.PersonConstraint;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ISavePersonUseCase {

    Mono<PersonOut> save(PersonIn person);

    @JsonTypeName(value = "person")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @PersonConstraint
    class PersonIn {  // event in, validation, operations

        private Long cpf;

        private String nome;

        private Integer idade;

        private BigDecimal salario;

        private double altura;

        private LocalDateTime dataNascimento;

        private boolean solteiro;

        private final List<String> errorValidationMessages = new ArrayList<>(7); // number of fields
    }

    @JsonTypeName(value = "person")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    @Builder
    @Getter
    @ToString
    class PersonOut {  // event out, operations

        private final long cpf;

        private final String nome;
    }
}