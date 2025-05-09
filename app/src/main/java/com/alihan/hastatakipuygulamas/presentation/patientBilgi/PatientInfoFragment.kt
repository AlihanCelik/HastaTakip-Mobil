package com.alihan.hastatakipuygulamas.presentation.patientBilgi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PatientInfoFragment : Fragment() {
    private var _binding: FragmentPatientInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PatientInfoViewModel by viewModels()
    private var hasta: Hasta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPatientInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        arguments.let {
            hasta=PatientInfoFragmentArgs.fromBundle(it!!).hasta
        }
        binding.hasta=hasta

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}