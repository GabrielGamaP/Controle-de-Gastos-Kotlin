package br.edu.infnet.android.teste3.ui.addTransicao

import androidx.lifecycle.*
import br.edu.infnet.android.teste3.databinding.FragmentAdicionarTransicaoBinding
import br.edu.infnet.android.teste3.dominio.Either
import br.edu.infnet.android.teste3.dominio.TransicaoCard
import br.edu.infnet.android.teste3.dominio.TransicaoCardValidation
import br.edu.infnet.android.teste3.usecase.SaveToDatabaseUseCase
import kotlinx.coroutines.launch

class AdicionarTransicaoViewModel(
    private val saveToDatabaseUseCase: SaveToDatabaseUseCase
) : ViewModel() {


    private val _newCard = MutableLiveData<TransicaoCard>().apply { value = null }
    val newCard: LiveData<TransicaoCard>
        get() = _newCard


    private val _receivedCard = MutableLiveData<TransicaoCard>(null)
    val receivedCard: TransicaoCard?
        get() = _receivedCard.value

    val showEditLabel = Transformations.map(_receivedCard) {
        it != null
    }

    private var _errorSnackbar = MutableLiveData<String?>(null)
    val errorSnackbar: LiveData<String?>
        get() = _errorSnackbar

    val nomeField = MutableLiveData<String>("")
    val valorField = MutableLiveData<String>("")
    val tipoField = MutableLiveData<String>("")
    val categoriaField = MutableLiveData<String>("")
    val quandoField = MutableLiveData<String>("")


    fun createNewCard() {
        val mBusinessCard = TransicaoCard(

            nome = nomeField.value.toString(),
            valor = valorField.value.toString(),
            tipo = tipoField.value.toString(),
            categoria = categoriaField.value.toString(),
            quando = quandoField.value.toString()



        )
        val either = TransicaoCardValidation.validate(mBusinessCard) as Either.BusinessCardEither
        with(either) {
            transicaoCard?.let {
                _newCard.value = it
                saveCard()
            }
            exception?.let {
                _errorSnackbar.value = it.message
                errorSnackbarShown()
            }
        }
    }

    private fun errorSnackbarShown() {
        _errorSnackbar.value = null
    }


    private fun saveCard() {
        if (receivedCard != null) {
            launchDataSave {
                newCard.value?.let {
                    saveToDatabaseUseCase.updateCardInDatabase(
                        it.copy(id = receivedCard?.id!!)
                    )
                }
            }
        } else {
            launchDataSave {
                newCard.value?.let {
                    saveToDatabaseUseCase
                        .saveNewCardToDatabase(it)
                }
            }

        }
        doneNavigateToHomeFragment()
    }

    private fun doneNavigateToHomeFragment() {
        _newCard.value = null
    }

    private fun launchDataSave(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (ex: Exception) {
                _errorSnackbar.value = ex.message
            }
        }
    }

    fun receiveCard(transicaoCard: TransicaoCard) {
        with(transicaoCard) {
            _receivedCard.value = transicaoCard
            initFields(this)
        }
    }

    private fun initFields(transicaoCard: TransicaoCard) {
        with(transicaoCard) {

            nomeField.value = nome
            valorField.value = valor
            tipoField.value = tipo
            categoriaField.value = categoria
            quandoField.value = quando
        }
    }



}