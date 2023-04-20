package br.edu.infnet.android.teste3.usecase

import androidx.lifecycle.LiveData
import br.edu.infnet.android.teste3.database.TransicaoCardRepository
import br.edu.infnet.android.teste3.dominio.TransicaoCard


/**
 * Esse use case é responsável pelas operações de leitura dos dados do repositório
 */
class DataBaseUseCase (repository: TransicaoCardRepository) {

    val listBusinessCard: LiveData<List<TransicaoCard>> =
        repository.listBusinessCard

}


class RemoveFromDatabaseUseCase (private val repository: TransicaoCardRepository) {

    suspend fun remove(transicaoCard: TransicaoCard) {
        repository.remove(transicaoCard)
    }

}
class SaveToDatabaseUseCase(private val repository: TransicaoCardRepository) {

    suspend fun saveNewCardToDatabase(
        transicaoCard: TransicaoCard,
    ) {
        repository.save(transicaoCard)
    }

    suspend fun updateCardInDatabase(
        transicaoCard: TransicaoCard,
    ) {
        repository.update(transicaoCard)
    }

}