package the.store.presentation.workers.models

import com.example.core.domain.models.db_entity.WorkerEntity

data class WorkersUiState(
    val searchedName: String = "",
    val workersList: List<WorkerEntity> = listOf()
)

val workersList by lazy {
    listOf(
        WorkerEntity(
            id = 0,
            name = "Misha",
            surname = "Vorozhbyt",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerEntity(
            id = 1,
            name = "Misha 2",
            surname = "Vorozhbyt ",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerEntity(
            id = 2,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 3,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 4,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 5,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 6,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerEntity(
            id = 7,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerEntity(
            id = 1,
            name = "Misha 2",
            surname = "Vorozhbyt ",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerEntity(
            id = 2,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 3,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 4,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 5,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),   WorkerEntity(
            id = 6,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
        WorkerEntity(
            id = 7,
            name = "Misha",
            surname = "Vorozhbyt 3",
            password = "",
            phone = "",
            emailAddress = "",
        ),
    )
}
