package com.alihan.hastatakipuygulamas.presentation.randevu

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientListBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentRandevuBinding
import com.alihan.hastatakipuygulamas.presentation.Adapter.DoktorAdapter
import com.alihan.hastatakipuygulamas.presentation.Adapter.PatientAdapter
import com.alihan.hastatakipuygulamas.presentation.Adapter.SpinnerDoktorAdapter
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class RandevuFragment : Fragment() {
    private var _binding: FragmentRandevuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RandevuViewModel by viewModels()
    private var isDoctorListLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandevuBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.randevuTarihi.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    binding.randevuTarihi.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        binding.randevuSaati.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    binding.randevuSaati.setText(selectedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePicker.show()
        }
        val durumlar = arrayOf("Seçiniz","Tamamlandı", "Planlandı", "Onaylandı")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, durumlar)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.randevuDurumuSpinner.adapter = adapter

        binding.randevuDurumuSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedDurum = parentView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is DoktorListState.Loading -> {
                }
                is DoktorListState.Success -> {
                    val doktorlar = state.patients
                    val doktorAdapter = SpinnerDoktorAdapter(requireContext(), doktorlar)
                    binding.doktorSpinner.adapter = doktorAdapter
                }
                is DoktorListState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.doktorSpinner.setOnTouchListener { _, _ ->
            if (!isDoctorListLoaded) {
                viewModel.fetchDoctors()
                isDoctorListLoaded = true
            }
            false
        }
        return binding.root
    }


}