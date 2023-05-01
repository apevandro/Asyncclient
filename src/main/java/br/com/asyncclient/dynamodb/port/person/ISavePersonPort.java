package br.com.asyncclient.dynamodb.port.person;

import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonIn;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonOut;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ISavePersonPort {

    Mono<PersonOut> save(PersonIn productIn);  // EventIn, EventOut
}