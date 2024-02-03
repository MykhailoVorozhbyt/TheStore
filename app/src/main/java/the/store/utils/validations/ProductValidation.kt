package the.store.utils.validations

import com.example.core.utils.elvis
import com.example.core.utils.helpers.emptyFieldErrorState
import com.example.core.utils.helpers.invalidatePriceErrorState
import the.store.presentation.products.product.models.ProductErrorState

fun isProductNameValid(name: String): ProductErrorState {
    if (name.isBlank()) {
        return ProductErrorState(nameErrorState = emptyFieldErrorState)
    }
    return ProductErrorState()
}

fun isProductPriceValid(price: String): ProductErrorState {
    try {
        if (price.isBlank()) {
            return ProductErrorState(priceErrorState = emptyFieldErrorState)
        }
        val priceDouble = price.toDoubleOrNull().elvis()
        if (priceDouble <= 0.0) {
            return ProductErrorState(priceErrorState = invalidatePriceErrorState)
        }
        return ProductErrorState()
    } catch (e: Exception) {
        return ProductErrorState()
    }
}

fun isProductPriceValid(price: Double): ProductErrorState {
    try {
        if (price <= 0.0) {
            return ProductErrorState(priceErrorState = invalidatePriceErrorState)
        }
        return ProductErrorState()
    } catch (e: Exception) {
        return ProductErrorState()
    }
}

fun isProductValidateInputs(
    name: String,
    price: Double
): ProductErrorState {
    val nameValidate: ProductErrorState = isProductNameValid(name)
    val priceValidate: ProductErrorState = isProductPriceValid(price)
    return when {
        nameValidate.nameErrorState.hasError -> nameValidate
        priceValidate.priceErrorState.hasError -> priceValidate
        else -> ProductErrorState()
    }
}