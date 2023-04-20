package br.edu.infnet.android.teste3.extensao

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import br.edu.infnet.android.teste3.dominio.TransicaoCard
import br.edu.infnet.android.teste3.ui.adapter.DataCard

fun LiveData<List<TransicaoCard>>.sortedByName(): LiveData<List<TransicaoCard>> =
    this.map { list ->
        list.sortedBy { transicaoCard ->
            transicaoCard.nome
        }
    }

fun List<TransicaoCard>.toListOfDataItem(): List<DataCard> {

    val grouping = this.groupBy { transicaoCard ->
        transicaoCard.nome.first()
    }

    val listDataItem = mutableListOf<DataCard>()
    grouping.forEach { mapEntry ->
        listDataItem.add(DataCard.Header(mapEntry.key))
        listDataItem.addAll(
            mapEntry.value.map { transicaoCard ->
                DataCard.TransicaoDataCard(transicaoCard)
            }
        )
    }

    return listDataItem
}