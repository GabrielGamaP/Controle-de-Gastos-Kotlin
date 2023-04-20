package br.edu.infnet.android.teste3.ui.dashboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.android.teste3.R
import br.edu.infnet.android.teste3.databinding.FragmentDashboardBinding
import br.edu.infnet.android.teste3.dominio.TransicaoCard
import br.edu.infnet.android.teste3.ui.adapter.TransicaoCardAdapter
import br.edu.infnet.android.teste3.ui.adapter.TransicaoCardListener
import br.edu.infnet.android.teste3.util.Image
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }




    private val mHomeViewModel: DashboardViewModel by viewModel()
    private val binding: FragmentDashboardBinding by lazy {
        FragmentDashboardBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.viewModel = mHomeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initTransicaoCardList()
        initNavigateToAddCardObserver()
        return binding.root
    }

    private fun initTransicaoCardList() = with(binding){


        mHomeViewModel.filteredListBusinessCard.observe(viewLifecycleOwner) {

            val (totalReceita, totalDespesa) = it.partition { it.tipo == "Receita" }
            val receita = totalReceita.sumOf { it.valor.toDouble() }
            val despesa = totalDespesa.sumOf { it.valor.toDouble() }
            incomeCardView.total2.text = "+ R$".plus(receita)
            expenseCardView.total.text = "- R$".plus(despesa)
            totalBalanceView.totalBalance.text = ("R$".plus(receita - despesa))


            val adapter = TransicaoCardAdapter(TransicaoCardListener(clickListener = { transicaoCard ->
                navigateToEditarTransicao(transicaoCard)
            }, longClickListener = { transicaoCard ->
                showRemoveDialog(transicaoCard)
                true
            }, shareBtnClickListener = {
                context?.let { context -> Image.share(context, it) }
            }
            ))


            initSearchBar()

            binding.RecyclerTransacao.layoutManager = LinearLayoutManager(context)
            binding.RecyclerTransacao.adapter = adapter
            adapter.addHeadersAndSubmitList(it)
        }
    }

    private fun initSearchBar() {
        binding.homeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mHomeViewModel.setSearchQuery(it)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    mHomeViewModel.setSearchQuery(it)
                    return true
                }
                return false
            }
        })
    }

    private fun initNavigateToAddCardObserver() {
        mHomeViewModel.navigateToAddCardFragment.observe(viewLifecycleOwner) { navigateToAddCardFragment ->
            if (navigateToAddCardFragment == true) {
                navigateToAdicionarTransicao()
                mHomeViewModel.doneNavigateToAddCardFragment()
            }
        }
    }

    private fun showRemoveDialog(transicaoCard: TransicaoCard) {
        AlertDialog.Builder(activity)
            .setTitle(getString(R.string.dialog_title_delete))
            .setMessage(getString(R.string.dialog_delete_msg))
            .setNegativeButton(getString(R.string.label_no), null)
            .setPositiveButton(getString(R.string.label_yes),
                DialogInterface.OnClickListener { _, _ ->
                    mHomeViewModel.remove(transicaoCard)
                })
            .create()
            .show()
    }

    private fun navigateToAdicionarTransicao() {
        val direction = DashboardFragmentDirections.navegaParaAdicionaCartao(null)
        findNavController().navigate(direction)
    }

    private fun navigateToEditarTransicao(transicaoCard: TransicaoCard) {
        val direction = DashboardFragmentDirections.navegaParaAdicionaCartao(transicaoCard)
        findNavController().navigate(direction)

    }


}