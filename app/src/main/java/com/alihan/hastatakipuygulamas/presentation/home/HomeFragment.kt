package com.alihan.hastatakipuygulamas.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setupClickListeners() {
        binding.hastaIslemBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_patientListFragment)
        }
        binding.doktorBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_doktorListFragment)
        }
        binding.teshistedaviBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_teshisTedaviFragment)
        }
        binding.randevuIslemBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_randevuFragment)
        }
    }

}