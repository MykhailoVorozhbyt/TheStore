package the.store.presentation.products.product.models

import android.os.Parcelable
import com.example.core.base.states.FieldErrorState
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductErrorState(
    val nameErrorState: FieldErrorState = FieldErrorState(),
    val priceErrorState: FieldErrorState = FieldErrorState()
) : Parcelable
