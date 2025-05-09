package com.alihan.hastatakipuygulamas.presentation.doktorList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.databinding.FragmentDoktorListBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientListBinding
import com.alihan.hastatakipuygulamas.presentation.Adapter.DoktorAdapter
import com.alihan.hastatakipuygulamas.presentation.Adapter.PatientAdapter
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoktorListFragment : Fragment() {
    private var _binding: FragmentDoktorListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DoktorListViewModel by viewModels()
    private lateinit var adapter: DoktorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoktorListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.doktorEkleBtn.setOnClickListener {
            findNavController().navigate(R.id.action_doktorListFragment_to_addDoktorFragment)
        }
        adapter = DoktorAdapter()
        binding.doktorRcw.layoutManager = LinearLayoutManager(requireContext())
        binding.doktorRcw.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchDoctor()
        }
        binding.doktorEkleBtn.setOnClickListener{
            findNavController().navigate(R.id.action_doktorListFragment_to_addDoktorFragment)
        }

        viewModel!!.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DoktorListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.doktorRcw.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                }
                is DoktorListState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.doktorRcw.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    adapter.setData(state.patients)
                    binding.swipeRefresh.isRefreshing = false
                }
                is DoktorListState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.doktorRcw.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = state.message
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }
        parentFragmentManager.setFragmentResultListener("doktor_eklendi", viewLifecycleOwner) { _, result ->
            val eklendi = result.getBoolean("eklendi", false)
            if (eklendi) {
                viewModel.fetchDoctor() // Listeyi güncelle
            }
        }


        viewModel.fetchDoctor()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}