package br.com.asyncclient.web.domain.product.constraint;

import br.com.asyncclient.web.domain.product.ISaveProductUseCase.ProductIn;
import br.com.asyncclient.web.exception.WebValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductValidator implements ConstraintValidator<ProductConstraint, ProductIn> {

    @Override
    public boolean isValid(ProductIn productIn, ConstraintValidatorContext constraintValidatorContext) {
        productIn.getErrorValidationMessages().clear();
        validateName(productIn);
        validateBrand(productIn);
        return isValid(productIn);
    }

    private static void validateName(ProductIn productIn) {
        if (productIn.getName() == null || productIn.getName().isEmpty()) {
            productIn.getErrorValidationMessages().add(ProductFieldErrorMessageEnum.byDesc("name").getFunction().apply("name"));
        }
    }

    private static void validateBrand(ProductIn productIn) {
        if (productIn.getBrand() == null || productIn.getBrand().isEmpty()) {
            productIn.getErrorValidationMessages().add(ProductFieldErrorMessageEnum.byDesc("brand").getFunction().apply("brand"));
        }
    }

    private static boolean isValid(ProductIn productIn) {
        if (!productIn.getErrorValidationMessages().isEmpty()) {
            throw new WebValidationException(productIn.getErrorValidationMessages());
        }
        return true;
    }
}