package com.alihan.hastatakipuygulamas.presentation.doktorInfo

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.FragmentDoktorInfoBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentDoktorListBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientInfoBinding
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListState
import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientDeleteState
import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientInfoFragmentArgs
import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientInfoFragmentDirections
import com.alihan.hastatakipuygulamas.presentation.patientBilgi.PatientInfoViewModel
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListFragmentDirections
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
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
        parentFragmentManager.setFragmentResultListener("doktor_guncellendi", viewLifecycleOwner) { _, bundle ->
            val guncellenmisDoktor= bundle.getParcelable<Doktor>("doktor")
            if (guncellenmisDoktor != null) {
                doktor = guncellenmisDoktor
                binding.doktor = doktor
            }
        }

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.delete.setOnClickListener {
            showDialog()

        }
        binding.dZenle.setOnClickListener {
            val action = DoktorInfoFragmentDirections.actionDoktorInfoFragmentToAddDoktorFragment(doktor!!)
            findNavController().navigate(action)
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DoktorDeleteState.Loading -> {
                }
                is DoktorDeleteState.Success -> {
                    Snackbar.make(binding.root, "Doktor başarıyla silindi", Snackbar.LENGTH_SHORT).show()
                    setFragmentResult("doktor_eklendi", bundleOf("eklendi" to true))
                    findNavController().popBackStack()
                }
                is DoktorDeleteState.Error -> {
                    Snackbar.make(binding.root, "Doktor silinemedi", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        binding.doktor=doktor
        return binding.root
    }

    private fun showDialog() {
        val dialogView = View.inflate(context, R.layout.delete_dialog, null)


        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val dialogtext=dialogView.findViewById<TextView>(R.id.delete_dialog_text)
        val cancelButton = dialogView.findViewById<MaterialButton>(R.id.no_trashdelete_permi)
        val deleteButton = dialogView.findViewById<MaterialButton>(R.id.evet_btn)

        dialogtext.text="Doktoru silmek istediğinize emin misiniz?"
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        deleteButton.setOnClickListener {
            doktor?.id?.let { id ->
                viewModel.deleteDoktor(id)
            }
            dialog.dismiss()
        }

        dialog.show()
    }


}