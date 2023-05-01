package br.com.asyncclient.it.template;

import br.com.asyncclient.web.domain.product.ISaveProductUseCase.ProductIn;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class ProductTemplateLoader implements CommonTemplateLoader {

    @Override
    public void load() {
        Fixture.of(ProductIn.class)
                .addTemplate(VALID_USE_CASE, new Rule() {{
                    add("name", random(STRING));
                    add("brand", random(STRING));
                }});
    }

}