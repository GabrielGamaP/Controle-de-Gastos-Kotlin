package br.edu.infnet.android.teste3.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import br.edu.infnet.android.teste3.dominio.TransicaoCard


class AplicarFiltroBuscaUseCase {

    fun filterList(
        list: LiveData<List<TransicaoCard>>,
        searchQuery: LiveData<CharSequence>
    ): LiveData<List<TransicaoCard>> =
        Transformations.switchMap(searchQuery) {
            list.map { list ->
                searchQuery.value?.let {
                    list.filter { transicaoCard ->
                        val query = searchQuery.value.toString()
                        with(transicaoCard) {
                            applyQuery(query)
                        }
                    }
                }
            }
        }

    private fun TransicaoCard.applyQuery(query: String) =
                nome.contains(query, true) ||
                valor.contains(query, true) ||
                tipo.contains(query, true)||
                categoria.contains(query, true) ||
                quando.contains(query, true)
}