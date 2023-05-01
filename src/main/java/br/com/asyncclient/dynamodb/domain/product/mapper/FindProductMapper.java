package br.com.asyncclient.dynamodb.domain.product.mapper;

import br.com.asyncclient.dynamodb.domain.product.ProductModel;
import br.com.asyncclient.web.domain.product.IFindProductUseCase.ProductOut;

public class FindProductMapper {

    public static ProductOut toEventOut(ProductModel model) {  // Model -> EventOut
        return ProductOut.builder()
                .id(model.getId())
                .name(model.getName())
                .brand(model.getBrand())
                .build();
    }

}