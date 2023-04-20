package br.edu.infnet.android.teste3.dominio

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "table_transicaocard")
data class TransicaoCard(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,

    val nome: String,
    val valor: String,
    val tipo: String,
    val categoria:String,
    val quando: String
) : Parcelable
