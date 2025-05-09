package com.alihan.hastatakipuygulamas.presentation.addPatient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.databinding.FragmentAddPatientBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientListBinding
import com.alihan.hastatakipuygulamas.presentation.Adapter.PatientAdapter
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddPatientFragment : Fragment() {
    private var _binding: FragmentAddPatientBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddPatientViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

}