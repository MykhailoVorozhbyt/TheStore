package the.store.presentation.basket

import com.example.core.base.states.BaseViewState
import com.example.core.base.vm.MviViewModel
import com.example.core.data.repository.ProductsRepository
import com.example.core.data.repository.SaleRepository
import com.example.core.domain.db_entity.check.CheckDbEntity
import com.example.core.domain.entities.CheckProductEntity
import com.example.core.domain.entities.ProductEntity
import com.example.core.domain.mapper.DomainMapper
import com.example.core.domain.mapper.mapToCheckProductDbEntity
import com.example.core.domain.mapper.mapToCheckProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import the.store.presentation.basket.models.BaseUiEvent
import the.store.presentation.basket.models.BasketSheetUiEvent
import the.store.presentation.basket.models.BasketUiEvent
import the.store.presentation.basket.models.BasketUiState
import the.store.utils.getTimeInMillis
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: ProductsRepository,
    private val saleRepository: SaleRepository,
    private val mapper: DomainMapper
) : MviViewModel<BaseViewState<BasketUiState>, BaseUiEvent>() {

    override fun onTriggerEvent(eventType: BaseUiEvent) {
        when (eventType) {
            is BasketSheetUiEvent -> onTriggerEvent(eventType)
            is BasketUiEvent -> onTriggerEvent(eventType)
        }
    }

    private fun onTriggerEvent(event: BasketSheetUiEvent) {
        when (event) {
            is BasketSheetUiEvent.SalleClick -> triggerEvent(event)
            is BasketSheetUiEvent.DeleteAllProductsClick -> triggerEvent(event)
            is BasketSheetUiEvent.DeleteProductClick -> triggerEvent(event)
            is BasketSheetUiEvent.MinusQuantityClick -> triggerEvent(event)
            is BasketSheetUiEvent.PlusQuantityClick -> triggerEvent(event)
        }
    }

    private fun onTriggerEvent(event: BasketUiEvent) {
        when (event) {
            is BasketUiEvent.InitUi -> triggerEvent(event)
            is BasketUiEvent.SearchProduct -> triggerEvent(event)
            is BasketUiEvent.ProductClick -> triggerEvent(event)
        }
    }

    private fun triggerEvent(event: BasketUiEvent.InitUi) {
        startLoading()
        getAllProducts()
    }

    private fun triggerEvent(event: BasketUiEvent.SearchProduct) {
        getProductsByName(event.value)
    }

    private fun triggerEvent(event: BasketUiEvent.ProductClick) {
        addProductToBasket(event.product)
    }

    private fun triggerEvent(event: BasketSheetUiEvent.SalleClick) {
        salleCheck()
    }

    private fun triggerEvent(event: BasketSheetUiEvent.DeleteAllProductsClick) {
        deleteAllProductsFromBasket()
    }

    private fun triggerEvent(event: BasketSheetUiEvent.PlusQuantityClick) {
        plusOrMinusQuantity(true, event.productId)
    }

    private fun triggerEvent(event: BasketSheetUiEvent.MinusQuantityClick) {
        plusOrMinusQuantity(false, event.productId)
    }

    private fun triggerEvent(event: BasketSheetUiEvent.DeleteProductClick) {
        deleteProductFromBasketById(event.productId)
    }

    private fun setNewDataState(state: BasketUiState) {
        setState(BaseViewState.Data(state))
    }

    private fun plusOrMinusQuantity(plus: Boolean, productId: Long) {
        successLaunch {
            val state = getCurrentState<BasketUiState>()
            val oldItem = state.basketProducts.find { it.id == productId }?.copy()
            val itemIndex = state.basketProducts.indexOf(oldItem)

            if (oldItem == null) return@successLaunch

            val newQuantity = if (plus) oldItem.quantity + 1 else oldItem.quantity - 1

            if (newQuantity <= 0) {
                deleteProductFromBasketById(productId)
                return@successLaunch
            }

            val newItem = oldItem.copy(
                quantity = newQuantity,
                fullPrice = oldItem.price * newQuantity
            )

            val list: MutableList<CheckProductEntity> = mutableListOf()
            list.addAll(state.basketProducts)
            list.remove(oldItem)
            list.add(itemIndex, newItem)

            val basketFullPrice = list.fold(0.0) { value, data -> value + data.fullPrice }

            setNewDataState(
                state.copy(
                    basketFullPrice = basketFullPrice,
                    basketProducts = list
                )
            )
        }
    }

    private fun deleteAllProductsFromBasket() {
        successLaunch {
            val state = getCurrentState<BasketUiState>()
            setNewDataState(
                state.copy(
                    basketFullPrice = 0.0,
                    basketProducts = listOf()
                )
            )
        }
    }

    private fun deleteProductFromBasketById(productId: Long) {
        successLaunch {
            val state = getCurrentState<BasketUiState>()
            val list: MutableList<CheckProductEntity> = mutableListOf()
            list.addAll(state.basketProducts)
            if (list.isNotEmpty()) {
                val item = list.find { it.id == productId }
                list.remove(item)
                val basketFullPrice = list.fold(0.0) { value, data -> value + data.fullPrice }
                setNewDataState(
                    state.copy(
                        basketFullPrice = basketFullPrice,
                        basketProducts = list
                    )
                )
            }
        }
    }

    private fun addProductToBasket(product: ProductEntity) {
        successLaunch {
            val state = getCurrentState<BasketUiState>()
            val productForBasket = product.mapToCheckProductEntity()
            if (state.basketProducts.isEmpty() ||
                state.basketProducts.contains(productForBasket).not()
            ) {
                val list: MutableList<CheckProductEntity> = mutableListOf()
                list.addAll(state.basketProducts)
                list.add(productForBasket)
                val basketFullPrice = list.fold(0.0) { value, data -> value + data.fullPrice }
                setNewDataState(
                    state.copy(
                        basketFullPrice = basketFullPrice,
                        basketProducts = list
                    )
                )
            }
        }
    }

    private fun getProductsByName(name: String) {
        safeLaunch {
            val state = getCurrentState() as BasketUiState
            val workers = repository.getProductsByName(name)
            setNewDataState(
                state.copy(
                    searchedProduct = name,
                    productList = mapper.mapToProductsEntity(workers)
                )
            )
        }
    }

    private fun getAllProducts() {
        successLaunch {
            val products = repository.getAllProducts()
            setNewDataState(
                BasketUiState(
                    productList = mapper.mapToProductsEntity(products)
                )
            )
        }
    }

    private fun salleCheck() {
        successLaunch {
            val state = getCurrentState() as BasketUiState
            val checkId = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE
            val check = CheckDbEntity(
                id = checkId,
                checkPrice = state.basketFullPrice,
                createdAt = getTimeInMillis(),
                isCanceled = false
            )
            val checkProducts = state.basketProducts.map { it.mapToCheckProductDbEntity(checkId) }
            saleRepository.saleCheck(check)
            saleRepository.insertCheckProduct(checkProducts)
            setNewDataState(
                state.copy(
                    isCheckSold = true
                )
            )
        }
    }
}