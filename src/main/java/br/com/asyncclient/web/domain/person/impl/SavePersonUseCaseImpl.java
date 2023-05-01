package br.com.asyncclient.web.domain.person.impl;

import br.com.asyncclient.dynamodb.port.person.ISavePersonPort;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SavePersonUseCaseImpl implements ISavePersonUseCase {

    private final ISavePersonPort savePersonPort;

    @Override
    public Mono<PersonOut> save(PersonIn personIn) {
        return savePersonPort.save(personIn);
    }

}