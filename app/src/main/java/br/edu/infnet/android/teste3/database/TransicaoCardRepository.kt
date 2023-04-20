package br.edu.infnet.android.teste3.database

import androidx.lifecycle.LiveData
import br.edu.infnet.android.teste3.dominio.TransicaoCard

class TransicaoCardRepository (
    private val transicaoCardDao: TransicaoCardDao
){

    /**
     * Encapsulando o acesso ao dao numa propriedade. Como o método getAll() não
     * é uma função de suspensão não preciso tomar nenhum cuidado especial com relação
     * às corrotinas.
     */
    val listBusinessCard: LiveData<List<TransicaoCard>>
        get() = transicaoCardDao.getAll()

    /**
     * O método save() precisa ser invocado a partir de
     * funções de suspensão. No ViewModel eu faço o tratamento das corrotinas,
     * invocando as operações dentro do viewModelScope.
     */
    suspend fun save(businessCard: TransicaoCard) {
        transicaoCardDao.insert(businessCard)
    }

    /**
     * Esse método apaga um BusinessCard do repositório. Também precisa ser uma
     * função de suspensão.
     */
    suspend fun remove(businessCard: TransicaoCard) {
        transicaoCardDao.remove(businessCard)
    }

    fun get(cardId: Long): TransicaoCard? = transicaoCardDao.get(cardId)

    suspend fun update(businessCard: TransicaoCard) {
        transicaoCardDao.update(businessCard)
    }


}