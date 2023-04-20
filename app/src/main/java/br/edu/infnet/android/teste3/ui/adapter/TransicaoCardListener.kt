package br.edu.infnet.android.teste3.ui.adapter

import android.view.View
import br.edu.infnet.android.teste3.dominio.TransicaoCard

class TransicaoCardListener(
    val clickListener: (businessCard: TransicaoCard) -> Unit,
    val shareBtnClickListener: (view: View) -> Unit,
    val longClickListener: (businessCard: TransicaoCard) -> Boolean
) {

    fun onClick(businessCard: TransicaoCard) {
        clickListener(businessCard)
    }

    fun onLongClick(businessCard: TransicaoCard) : Boolean {
        longClickListener(businessCard)
        return true
    }

    fun onShareBtnClick(view: View) {
        shareBtnClickListener(view)
    }

}