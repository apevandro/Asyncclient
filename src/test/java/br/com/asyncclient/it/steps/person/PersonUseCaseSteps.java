package br.com.asyncclient.it.steps.person;

import br.com.asyncclient.helper.BasePackageFactoryHelper;
import br.com.asyncclient.it.steps.IntegrationTestCommon;
import br.com.asyncclient.it.template.PersonTemplateLoader;
import br.com.asyncclient.web.domain.person.ISavePersonUseCase;
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
public class PersonUseCaseSteps extends IntegrationTestCommon implements Pt {

    private static final String POST_MAPPING = "/reactive/v1/person/";

    private ISavePersonUseCase.PersonIn savePersonIn;

    private static Response response;

    @Before
    public static void setup() {
        FixtureFactoryLoader.loadTemplates(BasePackageFactoryHelper.FACTORY_BASE_PACKAGE);
    }

    public PersonUseCaseSteps() {
        Dado("^o cenário Save Person$", () ->
                savePersonIn = Fixture.from(ISavePersonUseCase.PersonIn.class).gimme(PersonTemplateLoader.VALID_USE_CASE)
        );

        Quando("^eu executo o cenário Save Person$", () ->
                response = RestAssured.given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(savePersonIn)
                        .post(POST_MAPPING)
        );

        Então("^o cenário Save Person executa com sucesso$", () ->
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED.value())
        );
    }

}