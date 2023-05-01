package br.com.asyncclient.dynamodb.port.product;

import br.com.asyncclient.web.domain.product.ISaveProductUseCase;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ISaveProductPort {

    Mono<ISaveProductUseCase.ProductOut> save(ISaveProductUseCase.ProductIn productIn);  // EventIn, EventOut

}