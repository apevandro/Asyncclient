package br.com.asyncclient.dynamodb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Slf4j
@Configuration
public class DynamoDbConfig {

    private final String endpoint;

    public DynamoDbConfig(@Value("${aws.dynamodb.endpoint}") String endpoint) {
        this.endpoint = endpoint;
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient() {
        log.info("AsyncClient default");
        return DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(getDynamoDbAsyncClient()).build();
    }

    private DynamoDbAsyncClient getDynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder().credentialsProvider(ProfileCredentialsProvider.create("default")).region(Region.SA_EAST_1).endpointOverride(URI.create(endpoint)).build();
    }

}
