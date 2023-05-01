package br.com.asyncclient.it.template;

import br.com.six2six.fixturefactory.loader.TemplateLoader;

@FunctionalInterface
public interface CommonTemplateLoader extends TemplateLoader {

    String VALID_USE_CASE = "VALID_USE_CASE";
    Object[] STRING = {"rm", "ps", "dl", "mt"};
}