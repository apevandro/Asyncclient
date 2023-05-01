package br.com.asyncclient.it.steps.person;

import br.com.asyncclient.helper.BasePackageFactoryHelper;
import br.com.asyncclient.it.steps.IntegrationTestCommon;
import br.com.asyncclient.it.template.PersonTemplateLoader;
import br.com.asyncclient.web.ErrorMessage;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase;
import br.com.asyncclient.web.domain.person.constraint.PersonFieldErrorMessageEnum;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import io.cucumber.java.Before;
import io.cucumber.java8.Pt;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class PersonFaultInjectionSteps extends IntegrationTestCommon implements Pt {

    private static final String POST_MAPPING = "/reactive/v1/person/";

    private ISavePersonUseCase.PersonIn savePersonIn;

    private static Response response;

    @Before
    public static void setup() {
        FixtureFactoryLoader.loadTemplates(BasePackageFactoryHelper.FACTORY_BASE_PACKAGE);
    }

    public PersonFaultInjectionSteps() {
        Dado("^o cenário Person - Cpf Nulo$", () ->
                savePersonIn = Fixture.from(ISavePersonUseCase.PersonIn.class).gimme(PersonTemplateLoader.CPF_NULO)
        );

        Quando("^eu executo o cenário Person - Cpf Nulo$", () ->
                response = RestAssured.given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(savePersonIn)
                        .post(POST_MAPPING)
        );

        Então("^o cenário Person - Cpf Nulo executa com sucesso$", () -> {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            ErrorMessage errorMessage = response.as(ErrorMessage.class);
            String message = PersonFieldErrorMessageEnum.byDesc("cpf").getFunction().apply("cpf");
            assertThat(errorMessage.getMessages()).contains(message);
        });

        Dado("^o cenário Person - Data de nascimento inválida$", () ->
                savePersonIn = Fixture.from(ISavePersonUseCase.PersonIn.class).gimme(PersonTemplateLoader.DATA_NASCIMENTO_INVALIDA)
        );

        Quando("^eu executo o cenário Person - Data de nascimento inválida$", () ->
                response = RestAssured.given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(savePersonIn)
                        .post(POST_MAPPING)
        );

        Então("^o cenário Person - Data de nascimento inválida executa com sucesso$", () -> {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            ErrorMessage errorMessage = response.as(ErrorMessage.class);
            String message = PersonFieldErrorMessageEnum.byDesc("data_nascimento").getFunction().apply("data_nascimento");
            assertThat(errorMessage.getMessages()).contains(message);
        });
    }

}