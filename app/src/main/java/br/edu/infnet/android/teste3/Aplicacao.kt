package br.edu.infnet.android.teste3

import android.app.Application
import android.os.StrictMode
import br.edu.infnet.android.teste3.di.daoModule
import br.edu.infnet.android.teste3.di.repositoryModule
import br.edu.infnet.android.teste3.di.useCaseModule
import br.edu.infnet.android.teste3.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Aplicacao : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Aplicacao)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(daoModule)
            modules(useCaseModule)
        }

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }
}