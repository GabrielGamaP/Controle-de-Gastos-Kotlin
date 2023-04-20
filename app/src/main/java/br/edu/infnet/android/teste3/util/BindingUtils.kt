package br.edu.infnet.android.teste3.util

import androidx.databinding.BindingAdapter
import br.edu.infnet.android.teste3.R
import br.edu.infnet.android.teste3.dominio.TransicaoCard
import br.edu.infnet.android.teste3.ui.adapter.DataCard
import com.google.android.material.textview.MaterialTextView


@BindingAdapter("itemNomeTv")
fun MaterialTextView.setNome(item: TransicaoCard?) {
    item?.let{ transicaoCard ->
        text = transicaoCard.nome
    }
}

@BindingAdapter("itemCategoriaTv")
fun MaterialTextView.setCategoria(item: TransicaoCard?) {
    item?.let{ transicaoCard ->
        text = transicaoCard.categoria
    }
}

@BindingAdapter("itemValorTv")
fun MaterialTextView.setValor(item: TransicaoCard?) {
    item?.let{ transicaoCard ->
        text = transicaoCard.valor
    }
}

@BindingAdapter("itemTipoTv")
fun MaterialTextView.setTipo(item: TransicaoCard?) {
    item?.let{ transicaoCard ->
        text = transicaoCard.tipo
    }
}

@BindingAdapter("itemQuandoTv")
fun MaterialTextView.setQuando(item: TransicaoCard?) {
    item?.let{ transicaoCard ->
        text = transicaoCard.quando
    }
}


@BindingAdapter("cardFormFragmentLabel")
fun MaterialTextView.setLabel(isEdit: Boolean) {
    isEdit?.let {
        if (!it) {
            text = context.getString(R.string.description_add_card_fragment)
        } else {
            text = context.getString(R.string.description_edit_card_fragment)
        }
    }
}

@BindingAdapter("cardHeaderKey")
fun MaterialTextView.setKey(item: DataCard.Header){
    item?.let { item ->
        text = item.key.toString()
    }
}