package br.com.asyncclient.web.domain.product.constraint;

import lombok.Getter;

import java.util.function.Function;

/**
 * Strategy
 */
public enum ProductFieldErrorMessageEnum {

    NAME("name", (field) -> "O campo Name não pode ser nem nulo, nem vazio."),

    BRAND("brand", (field) -> "O campo Brand não pode ser nem nulo, nem vazio."),

    UNDEF("undef", (field) -> "undef");

    @Getter
    private final String description;

    @Getter
    private final Function<String, String> function;

    ProductFieldErrorMessageEnum(String description, Function<String, String> function) {
        this.description = description;
        this.function = function;
    }

    public static ProductFieldErrorMessageEnum byDesc(String field) {
        for (ProductFieldErrorMessageEnum type : ProductFieldErrorMessageEnum.values()) {
            if (type.getDescription().equalsIgnoreCase(field)) {
                return type;
            }
        }
        return UNDEF;
    }

}