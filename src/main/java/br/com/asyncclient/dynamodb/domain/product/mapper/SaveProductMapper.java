package br.com.asyncclient.dynamodb.domain.product.mapper;

import br.com.asyncclient.dynamodb.domain.product.ProductModel;
import br.com.asyncclient.web.domain.product.ISaveProductUseCase;
import br.com.asyncclient.web.domain.product.ISaveProductUseCase.ProductOut;

import java.util.UUID;

public class SaveProductMapper {

    public static ProductModel toModel(ISaveProductUseCase.ProductIn productIn) {  // EventIn -> Model
        return ProductModel.builder()
                .id(UUID.randomUUID().toString())
                .name(productIn.getName())
                .brand(productIn.getBrand())
                .build();
    }

    public static ISaveProductUseCase.ProductOut toEventOut(ProductModel model) {  // Model -> EventOut
        return ProductOut.builder()
                .id(model.getId())
                .name(model.getName())
                .brand(model.getBrand())
                .build();
    }

}