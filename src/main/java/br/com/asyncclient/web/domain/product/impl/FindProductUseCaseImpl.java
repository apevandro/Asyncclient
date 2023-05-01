package br.com.asyncclient.web.domain.product.impl;

import br.com.asyncclient.dynamodb.port.product.IFindProductPort;
import br.com.asyncclient.web.domain.product.IFindProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindProductUseCaseImpl implements IFindProductUseCase {

    private final IFindProductPort findProduct;

    @Override
    public Mono<ProductOut> find(String id) {
        return findProduct.find(id);
    }

    @Override
    public Flux<ProductOut> findAll() {
        return findProduct.findAll();
    }

}