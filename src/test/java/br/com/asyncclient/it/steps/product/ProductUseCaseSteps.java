package br.com.asyncclient.it.steps.product;

import br.com.asyncclient.helper.BasePackageFactoryHelper;
import br.com.asyncclient.it.steps.IntegrationTestCommon;
import br.com.asyncclient.it.template.ProductTemplateLoader;
import br.com.asyncclient.web.domain.product.IFindProductUseCase;
import br.com.asyncclient.web.domain.product.ISaveProductUseCase;
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
public class ProductUseCaseSteps extends IntegrationTestCommon implements Pt {

    private static final String POST_MAPPING = "/reactive/v1/product/";
    private static final String GET_MAPPING = "/reactive/v1/product/{id}";

    private ISaveProductUseCase.ProductIn saveProductIn;
    private ISaveProductUseCase.ProductOut saveProductOut;
    private IFindProductUseCase.ProductOut findProductOut;

    private String id;
    private static Response response;

    @Before
    public static void setup() {
        FixtureFactoryLoader.loadTemplates(BasePackageFactoryHelper.FACTORY_BASE_PACKAGE);
    }

    public ProductUseCaseSteps() {
        Dado("^o cenário Save Product$", () ->
                saveProductIn = Fixture.from(ISaveProductUseCase.ProductIn.class).gimme(ProductTemplateLoader.VALID_USE_CASE)
        );

        Quando("^eu executo o cenário Save Product$", () ->
                response = RestAssured
                        .given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(saveProductIn)
                        .post(POST_MAPPING)
        );

        Então("^o cenário Save Product executa com sucesso$", () ->
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED.value())
        );

        Dado("^o cenário Find Product$", () -> {
            saveProductOut = response.as(ISaveProductUseCase.ProductOut.class);
            id = saveProductOut.getId();
        });

        Quando("^eu executo o cenário Find Product$", () ->
                response = RestAssured.given().accept(ContentType.JSON)
                        .port(port)
                        .contentType(ContentType.JSON)
                        .get(GET_MAPPING, id)
                        .andReturn()
        );

        Então("^o cenário Find Product executa com sucesso$", () -> {
            findProductOut = response.as(IFindProductUseCase.ProductOut.class);
            assertThat(findProductOut.getId()).isEqualTo(id);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        });
    }

}