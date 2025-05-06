package com.alihan.hastatakipuygulamas.presentation.patientList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        binding.viewModel = viewModel
        adapter = PatientAdapter(viewModel.patients)

        binding.hastaRcw.adapter = adapter

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel.fetchPatients()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}