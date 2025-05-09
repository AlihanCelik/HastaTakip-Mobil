package com.alihan.hastatakipuygulamas.presentation.patientList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientListBinding
import com.alihan.hastatakipuygulamas.presentation.Adapter.PatientAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientListFragment : Fragment() {
    private var _binding: FragmentPatientListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PatientListViewModel by viewModels()
    private lateinit var adapter: PatientAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPatientListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        adapter = PatientAdapter()
        binding.hastaRcw.layoutManager = LinearLayoutManager(requireContext())
        binding.hastaRcw.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchPatients()
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PatientListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.hastaRcw.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                }
                is PatientListState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.hastaRcw.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    adapter.setData(state.patients)
                    binding.swipeRefresh.isRefreshing = false
                }
                is PatientListState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.hastaRcw.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = state.message
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }


        viewModel.fetchPatients()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}