package br.com.asyncclient.dynamodb.port.product.impl;

import br.com.asyncclient.dynamodb.domain.product.ProductRepository;
import br.com.asyncclient.dynamodb.domain.product.mapper.SaveProductMapper;
import br.com.asyncclient.dynamodb.port.product.ISaveProductPort;
import br.com.asyncclient.web.domain.product.ISaveProductUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveProductPortImpl implements ISaveProductPort {

    private final ProductRepository productRepository;

    @Override
    public Mono<ISaveProductUseCase.ProductOut> save(ISaveProductUseCase.ProductIn productIn) {  // EventIn, EventOut
        log.info("saving product item in dynamodb");
        return Mono.just(productIn).map(SaveProductMapper::toModel).flatMap(productRepository::save).map(SaveProductMapper::toEventOut);
    }

}
