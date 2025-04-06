package the.store.presentation.primary

import com.example.core.domain.db_entity.check.CheckProductDbEntity
import com.example.core.domain.entities.SaleHistoryEntity
import com.example.core.domain.utils.Currency
import com.example.core.domain.utils.Measurements

object PrimaryScreenPreviewProvider {

    fun getSaleHistoryEntity(): List<SaleHistoryEntity> {
        return listOf(
            SaleHistoryEntity(
                id = 0,
                saleId = 1L,
                createdAt = 1707916401514L,
                fullPrice = 155.0,
                products = getPrimaryBottomSheetContent()
            ),
            SaleHistoryEntity(
                id = 2,
                saleId = 2L,
                createdAt = 1707916401514L,
                fullPrice = 85474.0,
                products = getPrimaryBottomSheetContent(3)
            ),
            SaleHistoryEntity(
                id = 3,
                saleId = 3L,
                createdAt = 1707916401514L,
                fullPrice = 4544.0,
                products = getPrimaryBottomSheetContent(2)
            ),
        )
    }


    private fun getPrimaryBottomSheetContent(times: Int = 1): List<CheckProductDbEntity> {
        val list: MutableList<CheckProductDbEntity> = mutableListOf()
        repeat(times) {
            list.add(
                CheckProductDbEntity(
                    id = it.toLong(),
                    checkId = it.toLong(),
                    photoUri = "",
                    name = "Test",
                    price = 311.0,
                    fullPrice = 1313.0,
                    quantity = 10.0,
                    measurementId = Measurements.SINGLE_ITEM.id.toInt(),
                    currencyId = Currency.USD.id.toInt(),
                )
            )
        }
        return list
    }

}