package br.com.asyncclient.web.domain.product;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFindProductUseCase {

    Mono<ProductOut> find(String id);

    Flux<ProductOut> findAll();

    @JsonTypeName(value = "product")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    @Builder
    @Getter
    @ToString
    class ProductOut {    // event out, operations

        private final String id;

        private final String name;

        private final String brand;
    }
}