package br.edu.infnet.android.teste3.database

import androidx.lifecycle.LiveData
import androidx.room.*
import br.edu.infnet.android.teste3.dominio.TransicaoCard

@Dao
interface TransicaoCardDao {

    @Insert
    suspend fun insert(transicaoCard: TransicaoCard)

    @Query("SELECT * FROM table_transicaocard")
    fun getAll() : LiveData<List<TransicaoCard>>


    @Delete
    suspend fun remove(businessCard: TransicaoCard)

    @Query("SELECT * FROM table_transicaocard WHERE id = :key")
    fun get(key: Long) : TransicaoCard?

    @Update
    suspend fun update(businessCard: TransicaoCard)

}