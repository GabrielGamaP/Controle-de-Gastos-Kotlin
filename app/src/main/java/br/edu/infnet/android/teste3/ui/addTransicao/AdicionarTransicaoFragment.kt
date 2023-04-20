package br.edu.infnet.android.teste3.ui.addTransicao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.edu.infnet.android.teste3.R
import br.edu.infnet.android.teste3.databinding.FragmentAdicionarTransicaoBinding
import br.edu.infnet.android.teste3.di.transformIntoDatePicker
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AdicionarTransicaoFragment : Fragment() {

    private val mAddCardViewModel: AdicionarTransicaoViewModel by viewModel()
    private val binding: FragmentAdicionarTransicaoBinding by lazy {
        FragmentAdicionarTransicaoBinding.inflate(layoutInflater)
    }
    private val arguments by navArgs<AdicionarTransicaoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        arguments.transicaoCard.let {
            if (it != null) {
                mAddCardViewModel.receiveCard(it)
            }
        }

        binding.navController = findNavController()
        binding.lifecycleOwner = viewLifecycleOwner

        mAddCardViewModel.errorSnackbar.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                Log.i("BusinessCard", "snackBar msg: $it")
            }
        }

        binding.viewModel = mAddCardViewModel

        //AutoComplet Text View

        val categorias = resources.getStringArray(R.array.categorias)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_filter_dropdown, categorias)
        binding.etCategoria.setAdapter(arrayAdapter)

        val tipodeTransicao = resources.getStringArray(R.array.tipoTransicao)
        val arrayAdapterdois = ArrayAdapter(requireContext(), R.layout.item_filter_dropdown, tipodeTransicao)
        binding.etTipoDeTransicao.setAdapter(arrayAdapterdois)


        /////

        initNavigateHomeObserver()
        return binding.root
    }


    private fun initNavigateHomeObserver() {

        binding.etQuando.transformIntoDatePicker(
            requireContext(),
            "dd/MM/yyyy",
            Date()
        )

        mAddCardViewModel.newCard.observe(viewLifecycleOwner) {
            if (it != null) {
                navigateToDashboardFragment()
            }
        }
    }


    private fun navigateToDashboardFragment() {
        findNavController().popBackStack()
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            AdicionarTransicaoFragment().apply {

            }
    }
}