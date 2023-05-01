package br.com.asyncclient.web.domain.person.constraint;

import lombok.Getter;

import java.util.function.Function;

/**
 * Strategy
 */
public enum PersonFieldErrorMessageEnum {

    CPF("cpf", (field) -> "O campo Cpf não pode ser nem nulo, nem vazio, deve conter apenas números e ter 11 dígitos."),

    NOME("nome", (field) -> "O campo Nome não pode ser nem nulo, nem vazio."),

    IDADE("idade", (field) -> "O campo Idade não pode ser nulo e deve conter apenas números."),

    SALARIO("salario", (field) -> "O campo Salário não pode ser nulo."),

    DATA_NASCIMENTO("data_nascimento", (field) -> "O dia do nascimento não pode ser nem nulo, nem anterior ao dia 01-01-1900"),

    UNDEF("undef", (field) -> "undef");

    @Getter
    private final String description;

    @Getter
    private final Function<String, String> function;

    PersonFieldErrorMessageEnum(String description, Function<String, String> function) {
        this.description = description;
        this.function = function;
    }

    public static PersonFieldErrorMessageEnum byDesc(String field) {
        for (PersonFieldErrorMessageEnum type : PersonFieldErrorMessageEnum.values()) {
            if (type.getDescription().equalsIgnoreCase(field)) {
                return type;
            }
        }
        return UNDEF;
    }

}