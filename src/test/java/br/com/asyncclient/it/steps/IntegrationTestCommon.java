package br.com.asyncclient.it.steps;

import br.com.asyncclient.AsyncclientApplication;
import br.com.asyncclient.helper.BasePackageFactoryHelper;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AsyncclientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan({BasePackageFactoryHelper.FACTORY_BASE_PACKAGE})
@CucumberContextConfiguration
public abstract class IntegrationTestCommon {

    @LocalServerPort
    protected int port;

}