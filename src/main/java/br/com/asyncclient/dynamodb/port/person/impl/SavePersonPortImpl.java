package br.com.asyncclient.dynamodb.port.person.impl;

import br.com.asyncclient.dynamodb.domain.person.PersonRepository;
import br.com.asyncclient.dynamodb.domain.person.mapper.SavePersonMapper;
import br.com.asyncclient.dynamodb.port.person.ISavePersonPort;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonIn;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SavePersonPortImpl implements ISavePersonPort {

    private final PersonRepository personRepository;

    @Override
    public Mono<PersonOut> save(PersonIn productIn) {  // EventIn, EventOut
        log.info("saving person item in dynamodb");
        return Mono.just(productIn).map(SavePersonMapper::toModel).flatMap(personRepository::save).map(SavePersonMapper::toEventOut);
    }

}
