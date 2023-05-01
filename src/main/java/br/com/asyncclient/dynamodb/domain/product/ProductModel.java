package br.com.asyncclient.dynamodb.domain.product;

import br.com.asyncclient.dynamodb.BaseModel;
import br.com.asyncclient.dynamodb.config.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Model(tableName = "tb_product")
@DynamoDbBean
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductModel implements BaseModel {

    private String id;
    private String name;
    private String brand;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    @DynamoDbAttribute("brand")
    public String getBrand() {
        return brand;
    }

}