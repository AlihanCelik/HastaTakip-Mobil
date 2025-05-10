package com.alihan.hastatakipuygulamas.presentation.doktorInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.FragmentDoktorInfoBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentDoktorListBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientInfoBinding
import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientInfoFragmentArgs
import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientInfoViewModel
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoktorInfoFragment : Fragment() {
    private var _binding: FragmentDoktorInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DoktorInfoViewModel by viewModels()
    private var doktor: Doktor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoktorInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        arguments.let {
            doktor=DoktorInfoFragmentArgs.fromBundle(it!!).doktor
        }

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.doktor=doktor
        return binding.root
    }


}