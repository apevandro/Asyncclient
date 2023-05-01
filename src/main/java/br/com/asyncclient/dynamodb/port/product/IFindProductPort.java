package br.com.asyncclient.dynamodb.port.product;

import br.com.asyncclient.web.domain.product.IFindProductUseCase.ProductOut;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFindProductPort {

    Mono<ProductOut> find(String id);  // EventOut

    Flux<ProductOut> findAll();  // EventOut
}