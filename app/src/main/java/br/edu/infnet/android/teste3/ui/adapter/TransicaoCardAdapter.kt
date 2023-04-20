package br.edu.infnet.android.teste3.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.android.teste3.R
import br.edu.infnet.android.teste3.databinding.ItemTransicaoLayoutBinding
import br.edu.infnet.android.teste3.dominio.TransicaoCard
import br.edu.infnet.android.teste3.extensao.toListOfDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransicaoCardAdapter(val cardListener: TransicaoCardListener) : ListAdapter<DataCard,
        DataCardViewHolder>(TransicaoCardDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)


    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataCardViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> DataCardViewHolder.HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> DataCardViewHolder.TransicaoCardViewHolder.from(parent)
            else -> throw ClassCastException("ViewType desconhecido ${viewType}")
        }
    }



    override fun onBindViewHolder(holder: DataCardViewHolder, position: Int) {
        when (holder) {

            is DataCardViewHolder.TransicaoCardViewHolder -> {
                val item = getItem(position) as DataCard.TransicaoDataCard
                holder.bind(item.transicaoCard, cardListener)

            }
            is DataCardViewHolder.HeaderViewHolder -> {
                val item = getItem(position) as DataCard.Header
                holder.bind(item)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataCard.Header -> ITEM_VIEW_TYPE_HEADER
            is DataCard.TransicaoDataCard -> ITEM_VIEW_TYPE_ITEM
        }


    }

    fun addHeadersAndSubmitList(list: List<TransicaoCard>?) {

        adapterScope.launch {
            val listDataItem = list?.toListOfDataItem()
            withContext(Dispatchers.Main) {
                submitList(listDataItem)
            }
        }

    }

    class TransicaoCardDiffCallback : DiffUtil.ItemCallback<DataCard>() {

        override fun areItemsTheSame(oldItem: DataCard, newItem: DataCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataCard, newItem: DataCard): Boolean {
            return oldItem == newItem
        }

    }



}