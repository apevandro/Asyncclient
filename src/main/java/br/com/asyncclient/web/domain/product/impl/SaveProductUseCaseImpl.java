package br.com.asyncclient.web.domain.product.impl;

import br.com.asyncclient.dynamodb.port.product.ISaveProductPort;
import br.com.asyncclient.web.domain.product.ISaveProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaveProductUseCaseImpl implements ISaveProductUseCase {

    private final ISaveProductPort saveProductPort;

    @Override
    public Mono<ProductOut> save(ISaveProductUseCase.ProductIn productIn) {
        return saveProductPort.save(productIn);
    }

}