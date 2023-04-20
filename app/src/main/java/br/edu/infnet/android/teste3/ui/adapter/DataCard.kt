package br.edu.infnet.android.teste3.ui.adapter

import br.edu.infnet.android.teste3.dominio.TransicaoCard


sealed class DataCard {

    abstract val id: Long

    data class TransicaoDataCard(
        val transicaoCard: TransicaoCard,
        override val id: Long = transicaoCard.id
    ) : DataCard()

    data class Header(
        val key: Char,
        override val id:Long = key.code.toLong()
    ) : DataCard()

}