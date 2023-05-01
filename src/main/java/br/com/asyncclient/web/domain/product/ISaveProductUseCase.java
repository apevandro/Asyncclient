package br.com.asyncclient.web.domain.product;

import br.com.asyncclient.web.domain.product.constraint.ProductConstraint;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ISaveProductUseCase {

    Mono<ProductOut> save(ProductIn product);

    @JsonTypeName(value = "product")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @ProductConstraint
    class ProductIn {  // event in, validation, operations

        private String name;

        private String brand;

        private final List<String> errorValidationMessages = new ArrayList<>(2); // number of fields
    }

    @JsonTypeName(value = "product")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    @Builder
    @Getter
    @ToString
    class ProductOut {  // event out, operations

        private final String id;

        private final String name;

        private final String brand;
    }
}