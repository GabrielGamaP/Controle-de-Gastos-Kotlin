package br.edu.infnet.android.teste3.di

import android.app.DatePickerDialog
import android.content.Context
import br.edu.infnet.android.teste3.database.TransicaoCardDatabase
import br.edu.infnet.android.teste3.database.TransicaoCardRepository
import br.edu.infnet.android.teste3.ui.addTransicao.AdicionarTransicaoViewModel
import br.edu.infnet.android.teste3.ui.dashboard.DashboardViewModel
import br.edu.infnet.android.teste3.usecase.AplicarFiltroBuscaUseCase
import br.edu.infnet.android.teste3.usecase.DataBaseUseCase
import br.edu.infnet.android.teste3.usecase.RemoveFromDatabaseUseCase
import br.edu.infnet.android.teste3.usecase.SaveToDatabaseUseCase
import com.google.android.material.textfield.TextInputEditText
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

val viewModelModule = module {
    viewModel { DashboardViewModel(get(), get(), get()) }
    viewModel { AdicionarTransicaoViewModel(get()) }
}

val repositoryModule = module {
    single { TransicaoCardRepository(get()) }
}

val daoModule = module {
    single { TransicaoCardDatabase.getInstance(androidContext()).transicaoCardDao }
}

val useCaseModule = module {
    factory { SaveToDatabaseUseCase(get()) }
    factory { AplicarFiltroBuscaUseCase() }
    factory { RemoveFromDatabaseUseCase(get()) }
    factory { DataBaseUseCase(get()) }
}

fun TextInputEditText.transformIntoDatePicker(
    context: Context,
    format: String,
    maxDate: Date? = null
) {
    isFocusableInTouchMode = false
    isClickable = true
    isFocusable = false

    val myCalendar = Calendar.getInstance()
    val datePickerOnDataSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf = SimpleDateFormat(format, Locale.UK)
            setText(sdf.format(myCalendar.time))
        }

    setOnClickListener {
        DatePickerDialog(
            context,
            datePickerOnDataSetListener,
            myCalendar
                .get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).run {
            maxDate?.time?.also { datePicker.maxDate = it }
            show()
        }
    }
}