#### DYNAMODB-LOCAL

<p> Docker
    
    docker-compose up

###### BASH - comandos:
    
<p> Criar a tabela

    aws dynamodb create-table \
    --endpoint-url http://localhost:8000 \
    --table-name tb_product \
    --attribute-definitions AttributeName=id,AttributeType=S \
    --key-schema AttributeName=id,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

<p> Listar tabelas
 
     aws dynamodb list-tables \
     --endpoint-url http://localhost:8000


<p> Descrever a tabela

    aws dynamodb describe-table \
    --endpoint-url http://localhost:8000 \
    --table-name=tb_product


<p> Inserir um item na tabela

    aws dynamodb put-item \
    --endpoint-url=http://localhost:8000 \
    --table-name tb_product \
    --item '{ "id":{"S":"54124-d45d4-4544d-4544d"}, "name":{"S":"Costela Premium"},"brand":{"S":"Free Boy"}}' \
    --return-consumed-capacity TOTAL

<p> Listar a tabela

    aws dynamodb scan \
    --endpoint-url http://localhost:8000 \
    --table-name=tb_product

<p> Listar o item

    aws dynamodb get-item \
    --endpoint-url=http://localhost:8000 \
    --table-name tb_product \
    --key '{"id": {"S": "54124-d45d4-4544d-4544d"}}'

<p> Deletar a tabela
    
    aws dynamodb delete-table \
    --endpoint-url=http://localhost:8000 \
    --table-name tb_product
    
<p> Run unit-test profile
    
    mvn clean test -P unit-test
    
<p> Run integration-test profile
    
    mvn clean verify -P integration-test