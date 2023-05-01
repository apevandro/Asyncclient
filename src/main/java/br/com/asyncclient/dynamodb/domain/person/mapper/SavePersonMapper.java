package br.com.asyncclient.dynamodb.domain.person.mapper;

import br.com.asyncclient.dynamodb.domain.person.PersonModel;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonIn;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonOut;

public class SavePersonMapper {

    public static PersonModel toModel(PersonIn person) {  // EventIn -> Model
        return PersonModel.builder()
                .cpf(person.getCpf())
                .name(person.getNome())
                .idade(person.getIdade())
                .salario(person.getSalario())
                .altura(person.getAltura())
                .solteiro(person.isSolteiro())
                .build();
    }

    public static PersonOut toEventOut(PersonModel model) {  // Model -> EventOut
        return PersonOut.builder()
                .cpf(model.getCpf())
                .nome(model.getName())
                .build();
    }

}