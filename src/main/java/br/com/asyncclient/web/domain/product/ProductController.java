package br.com.asyncclient.web.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping(value = "/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ISaveProductUseCase saveProductUseCase;
    private final IFindProductUseCase findProductUseCase;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ISaveProductUseCase.ProductOut>> save(@Valid @RequestBody ISaveProductUseCase.ProductIn productIn) {  // EventIn, EventOut
        return saveProductUseCase
                .save(productIn)
                .map(productOut -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(productOut));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<IFindProductUseCase.ProductOut>> find(@PathVariable("id") String id) {
        return findProductUseCase
                .find(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<IFindProductUseCase.ProductOut> findAll() {
        return findProductUseCase.findAll().delayElements(Duration.ofMillis(1000));
    }

}