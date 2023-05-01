package br.com.asyncclient.dynamodb;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

public abstract class BaseRepository<T extends BaseModel> {

    public Mono<T> findById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        return Mono.fromCompletionStage(getAsyncTable().getItem(key));
    }

    public Mono<T> save(T model) {
        return Mono.just(model)
                .map(mod -> Mono.fromCompletionStage(getAsyncTable().putItem(mod)))
                .map(mod -> model);
    }

    public Mono<T> update(T model) {
        return Mono.fromCompletionStage(getAsyncTable().updateItem(model));
    }

    public Flux<T> findAll() {
        return Flux.from(getAsyncTable().scan().items());
    }

    protected abstract DynamoDbAsyncTable<T> getAsyncTable();

}