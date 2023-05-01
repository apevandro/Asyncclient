package br.com.asyncclient.dynamodb.domain.person;

import br.com.asyncclient.dynamodb.BaseRepository;
import br.com.asyncclient.dynamodb.config.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.lang.reflect.ParameterizedType;

@Slf4j
@Repository
public class PersonRepository extends BaseRepository<PersonModel> {

    private final DynamoDbAsyncTable<PersonModel> asyncTable;

    public PersonRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        log.info("AsyncTable for table: " + getClazz().getAnnotation(Model.class).tableName());
        asyncTable = asyncClient.table(getClazz().getAnnotation(Model.class).tableName(), TableSchema.fromBean(getClazz()));
    }

    @SuppressWarnings("unchecked")
    private Class<PersonModel> getClazz() {
        return (Class<PersonModel>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public DynamoDbAsyncTable<PersonModel> getAsyncTable() {
        return asyncTable;
    }

}
