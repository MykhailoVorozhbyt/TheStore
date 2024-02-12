package the.store.presentation.primary.models

import androidx.compose.runtime.Immutable
import com.example.core.domain.entities.CompanyEntity
import com.example.core.domain.entities.WorkerEntity

@Immutable
data class PrimaryUiState(
    val companyInfo: CompanyEntity? = null,
    val workerInfo: WorkerEntity? = null,
    val historySearch: String = "",
    val history: List<String> = historyList
)

val historyList = listOf(
    "1411",
    "141",
    "411",
    "214",
    "4211",
    "91",
    "1",
    "18",
    "14",
    "114",
    "1",
    "15",
    "21",
    "1",
    "11",
    "51",
    "16",
    "115",

    )
