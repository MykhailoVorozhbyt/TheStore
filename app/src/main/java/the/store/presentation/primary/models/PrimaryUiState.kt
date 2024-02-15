package the.store.presentation.primary.models

import androidx.compose.runtime.Immutable
import com.example.core.domain.entities.CompanyEntity
import com.example.core.domain.entities.SaleHistoryEntity
import com.example.core.domain.entities.WorkerEntity

@Immutable
data class PrimaryUiState(
    val companyInfo: CompanyEntity? = null,
    val workerInfo: WorkerEntity? = null,
    val historySearch: String = "",
    val history: List<SaleHistoryEntity> = listOf(),
    val error: String? = null
)