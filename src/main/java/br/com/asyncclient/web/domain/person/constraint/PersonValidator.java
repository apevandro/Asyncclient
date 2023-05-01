package br.com.asyncclient.web.domain.person.constraint;

import br.com.asyncclient.web.domain.person.ISavePersonUseCase;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonIn;
import br.com.asyncclient.web.exception.WebValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonValidator implements ConstraintValidator<PersonConstraint, PersonIn> {

    @Override
    public boolean isValid(ISavePersonUseCase.PersonIn personIn, ConstraintValidatorContext constraintValidatorContext) {
        personIn.getErrorValidationMessages().clear();
        validateCpf(personIn);
        validateNome(personIn);
        validateIdade(personIn);
        validateSalario(personIn);
        validateDataNascimento(personIn);
        return isValid(personIn);
    }

    private static void validateCpf(PersonIn personIn) {
        if (personIn.getCpf() == null
                || !personIn.getCpf().toString().matches("[0-9]+")
                || personIn.getCpf().toString().length() != 11) {
            personIn.getErrorValidationMessages()
                    .add(PersonFieldErrorMessageEnum.byDesc("cpf").getFunction().apply("cpf"));
        }
    }

    private static void validateNome(PersonIn personIn) {
        if (personIn.getNome() == null || personIn.getNome().isEmpty()) {
            personIn.getErrorValidationMessages()
                    .add(PersonFieldErrorMessageEnum.byDesc("nome").getFunction().apply("nome"));
        }
    }

    private static void validateIdade(PersonIn personIn) {
        if (personIn.getIdade() == null || !personIn.getIdade().toString().matches("[0-9]+")) {
            personIn.getErrorValidationMessages()
                    .add(PersonFieldErrorMessageEnum.byDesc("idade").getFunction().apply("idade"));
        }
    }

    private static void validateSalario(PersonIn personIn) {
        if (personIn.getSalario() == null) {
            personIn.getErrorValidationMessages()
                    .add(PersonFieldErrorMessageEnum.byDesc("salario").getFunction().apply("salario"));
        }
    }

    private static void validateDataNascimento(PersonIn personIn) {
        LocalDateTime limitDate = LocalDateTime.parse("1900-01-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (personIn.getDataNascimento() == null || personIn.getDataNascimento().isBefore(limitDate)) {
            personIn.getErrorValidationMessages()
                    .add(PersonFieldErrorMessageEnum.byDesc("data_nascimento").getFunction().apply("data_nascimento"));
        }
    }

    private static boolean isValid(PersonIn personIn) {
        if (!personIn.getErrorValidationMessages().isEmpty()) {
            throw new WebValidationException(personIn.getErrorValidationMessages());
        }
        return true;
    }
}