package the.store.presentation.workers.models

import androidx.compose.runtime.Stable
import com.example.core.domain.db_entity.WorkerDbEntity

@Stable
data class WorkersUiState(
    val isRefreshing: Boolean = false,
    val searchedName: String = "",
    val workersList: List<WorkerDbEntity> = listOf()
)

val workersList by lazy {
    listOf(
        WorkerDbEntity(
            id = 0,
            photoUri = "buffer.com/cdn-cgi/image/w=1000,fit=contain,q=90,f=auto/library/content/images/size/w600/2023/10/free-images.jpg",
            name = "Misha",
            surname = "Vorozhbyt",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 1,
            name = "Misha 2",
            surname = "Vorozhbyt ",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 2,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 3,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 4,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 5,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 6,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 7,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 1,
            name = "Misha 2",
            surname = "Vorozhbyt ",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 2,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 3,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 4,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 5,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 6,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerDbEntity(
            id = 7,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
    )
}
