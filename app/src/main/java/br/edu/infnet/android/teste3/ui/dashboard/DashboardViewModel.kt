package br.edu.infnet.android.teste3.ui.dashboard

import android.view.View
import androidx.lifecycle.*
import br.edu.infnet.android.teste3.dominio.TransicaoCard
import br.edu.infnet.android.teste3.extensao.sortedByName
import br.edu.infnet.android.teste3.usecase.AplicarFiltroBuscaUseCase
import br.edu.infnet.android.teste3.usecase.DataBaseUseCase
import br.edu.infnet.android.teste3.usecase.RemoveFromDatabaseUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(
    readFromDatabaseUseCase: DataBaseUseCase,
    private val removeFromDatabaseUseCase: RemoveFromDatabaseUseCase,
    applySearchFilterUseCase: AplicarFiltroBuscaUseCase,

    ) : ViewModel() {

    private val _listBusinessCard: LiveData<List<TransicaoCard>> =
        readFromDatabaseUseCase.listBusinessCard

    val listBusinessCard: LiveData<List<TransicaoCard>>
        get() = _listBusinessCard


    val showEmptyListMessage = Transformations.map(_listBusinessCard) {
        if (it.isEmpty()) {
            return@map View.VISIBLE
        } else return@map View.GONE
    }


    private val _searchQuery = MutableLiveData<CharSequence>("")
    val searchQuery: LiveData<CharSequence>
        get() = _searchQuery

    fun setSearchQuery(query: CharSequence?) {
        query?.let {
            _searchQuery.value = it
        }
    }

    val filteredListBusinessCard: LiveData<List<TransicaoCard>> =
        applySearchFilterUseCase.filterList(listBusinessCard, searchQuery).sortedByName()


    val navigateToAddCardFragment = MutableLiveData<Boolean>(false)


    fun navigateToAddCardFragment(): Unit {
        navigateToAddCardFragment.value = true
    }

    fun doneNavigateToAddCardFragment(): Unit {
        navigateToAddCardFragment.value = false
    }

    fun remove(transicaoCard: TransicaoCard) {
        viewModelScope.launch {
            removeFromDatabaseUseCase.remove(transicaoCard)
        }
    }
}