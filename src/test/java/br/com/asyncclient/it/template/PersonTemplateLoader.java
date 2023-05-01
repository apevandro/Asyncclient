package br.com.asyncclient.it.template;

import br.com.asyncclient.web.domain.person.ISavePersonUseCase.PersonIn;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonTemplateLoader implements CommonTemplateLoader {

    // Fault Injections
    public static final String CPF_NULO = "CPF_NULO";
    public static final String DATA_NASCIMENTO_INVALIDA = "DATA_NASCIMENTO_INVALIDA";

    @Override
    public void load() {

        Fixture.of(PersonIn.class)
                .addTemplate(VALID_USE_CASE, new Rule() {{
                    LocalDateTime dataNascimento = LocalDateTime.parse("1900-01-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    add("cpf", random(10000000000L, 99999999999L));
                    add("nome", random(STRING));
                    add("idade", random(18, 100));
                    add("salario", new BigDecimal("10.00"));
                    add("altura", 1.75);
                    add("dataNascimento", dataNascimento);
                    add("solteiro", true);
                }})
                .addTemplate(CPF_NULO).inherits(VALID_USE_CASE, new Rule() {{
            add("cpf", null);
        }})
                .addTemplate(DATA_NASCIMENTO_INVALIDA).inherits(VALID_USE_CASE, new Rule() {{
            LocalDateTime dataNascimento = LocalDateTime.parse("1899-01-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            add("dataNascimento", dataNascimento);
        }});
    }

}