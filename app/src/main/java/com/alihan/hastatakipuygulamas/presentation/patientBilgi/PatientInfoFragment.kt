package com.alihan.hastatakipuygulamas.presentation.patientBilgi

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientInfoBinding
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListFragmentDirections
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
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
        parentFragmentManager.setFragmentResultListener("hasta_guncellendi", viewLifecycleOwner) { _, bundle ->
            val guncellenmisHasta = bundle.getParcelable<Hasta>("hasta")
            if (guncellenmisHasta != null) {
                hasta = guncellenmisHasta
                binding.hasta = hasta
            }
        }
        binding.hasta=hasta
        binding.delete.setOnClickListener {
            showDialog()
        }
        binding.dZenle.setOnClickListener {
            val action = PatientInfoFragmentDirections
                .actionPatientInfoFragmentToAddPatientFragment(hasta!!)
            findNavController().navigate(action)
        }


        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PatientDeleteState.Loading -> {
                }
                is PatientDeleteState.Success -> {
                    Snackbar.make(binding.root, "Hasta başarıyla silindi", Snackbar.LENGTH_SHORT).show()
                    setFragmentResult("hasta_eklendi", bundleOf("eklendi" to true))
                    findNavController().popBackStack()
                }
                is PatientDeleteState.Error -> {
                    Snackbar.make(binding.root, "Hasta silinemedi", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialog() {
        val dialogView = View.inflate(context, R.layout.delete_dialog, null)


        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val cancelButton = dialogView.findViewById<MaterialButton>(R.id.no_trashdelete_permi)
        val deleteButton = dialogView.findViewById<MaterialButton>(R.id.evet_btn)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        deleteButton.setOnClickListener {
            hasta?.id?.let { id ->
                viewModel.deletePatients(id)
            }
            dialog.dismiss()
        }

        dialog.show()
    }

}