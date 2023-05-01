package br.com.asyncclient.dynamodb.port.product.impl;

import br.com.asyncclient.dynamodb.domain.product.ProductRepository;
import br.com.asyncclient.dynamodb.domain.product.mapper.FindProductMapper;
import br.com.asyncclient.dynamodb.port.product.IFindProductPort;
import br.com.asyncclient.web.domain.product.IFindProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindProductPortImpl implements IFindProductPort {

    private final ProductRepository productRepository;

    @Override
    public Mono<IFindProductUseCase.ProductOut> find(String id) {
        log.info("getting product item from dynamodb");
        return productRepository.findById(id).map(FindProductMapper::toEventOut);
    }

    @Override
    public Flux<IFindProductUseCase.ProductOut> findAll() {
        log.info("getting product items from dynamodb");
        return productRepository.findAll().map(FindProductMapper::toEventOut);
    }

}
