package br.com.asyncclient.web.domain.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final ISavePersonUseCase savePersonUseCase;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ISavePersonUseCase.PersonOut>> save(@Valid @RequestBody ISavePersonUseCase.PersonIn personIn) {  // EventIn, EventOut
        return savePersonUseCase
                .save(personIn)
                .map(personOut -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(personOut));
    }

}