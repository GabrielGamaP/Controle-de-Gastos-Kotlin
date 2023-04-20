package br.edu.infnet.android.teste3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.infnet.android.teste3.dominio.TransicaoCard

@Database(entities = [TransicaoCard::class], version = 1, exportSchema = false)
abstract class TransicaoCardDatabase : RoomDatabase() {


    abstract val transicaoCardDao: TransicaoCardDao

    companion object {

        @Volatile
        private var INSTANCE: TransicaoCardDatabase? = null

        fun getInstance(context: Context) : TransicaoCardDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TransicaoCardDatabase::class.java,
                        "transicaocard_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}