package com.alihan.hastatakipuygulamas.presentation.randevuList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.data.model.Randevu
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientListBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentRandevuListBinding
import com.alihan.hastatakipuygulamas.presentation.Adapter.PatientAdapter
import com.alihan.hastatakipuygulamas.presentation.Adapter.RandevuAdapter
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListViewModel
import com.alihan.hastatakipuygulamas.presentation.randevu.RandevuState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandevuListFragment : Fragment() {
    private var _binding: FragmentRandevuListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RandevuListViewModel by viewModels()
    private lateinit var adapter: RandevuAdapter
    private var hasta: Hasta? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandevuListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel
        arguments.let {
            hasta=RandevuListFragmentArgs.fromBundle(it!!).hasta
        }

        adapter = RandevuAdapter()
        binding.randevuRcw.layoutManager = LinearLayoutManager(requireContext())
        binding.randevuRcw.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchRandevu(hasta?.tcKimlikNo!!)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RandevuState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.randevuRcw.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                }
                is RandevuState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.randevuRcw.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    adapter.setData(state.randevu)
                    binding.swipeRefresh.isRefreshing = false
                }
                is RandevuState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.randevuRcw.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = state.message
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.fetchRandevu(hasta?.tcKimlikNo!!)
        return binding.root
    }


}