package br.edu.infnet.android.teste3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.edu.infnet.android.teste3.R
import br.edu.infnet.android.teste3.databinding.FragmentDashboardBinding
import br.edu.infnet.android.teste3.databinding.ItemLeitorBinding
import br.edu.infnet.android.teste3.databinding.ItemTransicaoLayoutBinding
import br.edu.infnet.android.teste3.dominio.TransicaoCard

sealed class DataCardViewHolder(open val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {


    class TransicaoCardViewHolder(override val binding: ItemTransicaoLayoutBinding) :


        DataCardViewHolder(binding) {

        fun bind(item: TransicaoCard, cardListener: TransicaoCardListener ) {
            with(binding) {


                when (item.tipo) {
                    "Receita" -> {

                        itemValorTv.setTextColor(
                            ContextCompat.getColor(
                                itemValorTv.context,
                                R.color.azul5
                            )
                        )



                    }
                    "Despesa" -> {

                        itemValorTv.setTextColor(
                            ContextCompat.getColor(
                                itemValorTv.context,
                                R.color.azul1
                            )
                        )
                    }
                }

                when (item.categoria) {
                    "Comida" -> {
                        itemIconeTV.setImageResource(R.drawable.ic_comida)
                    }
                    "Contas" -> {
                        itemIconeTV.setImageResource(R.drawable.ic_contas)
                    }
                    "Educação" -> {
                        itemIconeTV.setImageResource(R.drawable.ic_educacao)
                    }
                    "Lazer" -> {
                        itemIconeTV.setImageResource(R.drawable.ic_lazer)
                    }
                    "Saúde" -> {
                        itemIconeTV.setImageResource(R.drawable.ic_saude)
                    }
                }

                transicaoCard= item
                listener = cardListener
                executePendingBindings()

            }
        }

        companion object {
            fun from(parent: ViewGroup): TransicaoCardViewHolder {
                val binding: ItemTransicaoLayoutBinding = ItemTransicaoLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return TransicaoCardViewHolder(binding)
            }
        }
    }


    class HeaderViewHolder(override val binding: ItemLeitorBinding) :
        DataCardViewHolder(binding) {

        fun bind(item: DataCard.Header) {
            with(binding) {
                header = item
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val binding: ItemLeitorBinding = ItemLeitorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return HeaderViewHolder(binding)
            }
        }

    }
}