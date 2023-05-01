package br.com.asyncclient.dynamodb.domain.person;

import br.com.asyncclient.dynamodb.BaseModel;
import br.com.asyncclient.dynamodb.config.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
@Model(tableName = "tb_person")
public class PersonModel implements BaseModel {

    private long cpf;
    private String name;
    private Integer idade;
    private BigDecimal salario;
    private double altura;
    private boolean solteiro;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("cpf")
    public long getCpf() {
        return cpf;
    }

    @DynamoDbAttribute("name")
    public String getName() {
        return name;
    }

    @DynamoDbAttribute("idade")
    public Integer getIdade() {
        return idade;
    }

    @DynamoDbAttribute("salario")
    public BigDecimal getSalario() {
        return salario;
    }

    @DynamoDbAttribute("altura")
    public double getAltura() {
        return altura;
    }

    @DynamoDbAttribute("solteiro")
    public boolean isSolteiro() {
        return solteiro;
    }

}
