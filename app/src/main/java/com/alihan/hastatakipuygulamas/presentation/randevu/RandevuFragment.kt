package com.alihan.hastatakipuygulamas.presentation.randevu

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.DoktorRef
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.data.model.HastaRef
import com.alihan.hastatakipuygulamas.data.model.Randevu
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientListBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentRandevuBinding
import com.alihan.hastatakipuygulamas.presentation.Adapter.DoktorAdapter
import com.alihan.hastatakipuygulamas.presentation.Adapter.PatientAdapter
import com.alihan.hastatakipuygulamas.presentation.Adapter.SpinnerDoktorAdapter
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@AndroidEntryPoint
class RandevuFragment : Fragment() {
    private var _binding: FragmentRandevuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RandevuViewModel by viewModels()
    private var isDoctorListLoaded = false
    private var selectedDate: String = ""
    private var selectedTime: String = ""
    private var selectedDoktor: Doktor? = null
    private var hasta:Hasta?=null
    private var randevu:Randevu?=null
    private val durumlar = arrayOf("Seçiniz","Tamamlandı", "Planlandı", "Onaylandı")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hasta = it.getParcelable("hasta")
        }

        arguments?.let {
            viewModel.fetchDoctors()
            randevu=it.getParcelable("randevu")
        }


    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandevuBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        if(hasta!=null){
            binding.hastaAd.setText(hasta!!.ad)
            binding.hastaSoyad.setText(hasta!!.soyad)
            binding.hastaTc.setText(hasta!!.tcKimlikNo)
            binding.hastaAd.isEnabled=false
            binding.hastaSoyad.isEnabled=false
            binding.hastaTc.isEnabled=false
        }

        if(randevu==null){
            var ad = binding.hastaAd
            var soyad = binding.hastaSoyad
            var tc = binding.hastaTc
            var randevusaati = binding.randevuSaati
            var randevutarihi = binding.randevuTarihi

            val fields = listOf(ad, soyad, tc, randevusaati, randevutarihi)

            fun checkFields() {
                binding.kaydetBtn.isEnabled = fields.all { it.text.toString().isNotBlank() }
            }

            fields.forEach { editText ->
                editText.addTextChangedListener {
                    checkFields()
                }
            }
        }else{
            binding.kaydetBtn.isEnabled=true
        }

        randevu?.let { randevu ->
            binding.hastaAd.setText(randevu.hasta.ad)
            binding.hastaSoyad.setText(randevu.hasta.soyad)
            binding.hastaTc.setText(randevu.hasta.tcKimlikNo)
            binding.kaydetBtn.text = "Güncelle"

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            val parsedDateTime = LocalDateTime.parse(randevu.randevuTarihi, formatter)
            selectedDate = parsedDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy"))
            selectedTime = parsedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            binding.randevuTarihi.setText(selectedDate)
            binding.randevuSaati.setText(selectedTime)

        }


        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.randevuTarihi.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    selectedDate = "$dayOfMonth/${month + 1}/$year"
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
                    selectedTime = String.format("%02d:%02d", hourOfDay, minute) // BURADA atama
                    binding.randevuSaati.setText(selectedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePicker.show()
        }

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
        val durumIndex = durumlar.indexOf(randevu?.durum)
        if (durumIndex != -1) {
            binding.randevuDurumuSpinner.setSelection(durumIndex)
        }


        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is DoktorListState.Loading -> {
                }
                is DoktorListState.Success -> {
                    val doktorlar = state.patients
                    val doktorAdapter = SpinnerDoktorAdapter(requireContext(), doktorlar)
                    binding.doktorSpinner.adapter = doktorAdapter
                    val mevcutDoktor = randevu?.doktor
                    val doktorIndex = doktorlar.indexOfFirst { it.id == mevcutDoktor?.id }
                    if (doktorIndex != -1) {
                        binding.doktorSpinner.setSelection(doktorIndex)
                    }
                }
                is DoktorListState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.doktorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                selectedDoktor = parent?.getItemAtPosition(position) as? Doktor
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        viewModel.state2.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RandevuState.Loading -> {
                    binding.kaydetBtn.isEnabled = false
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                is RandevuState.Success -> {

                        Snackbar.make(binding.root, "Hasta başarıyla eklendi", Snackbar.LENGTH_SHORT).show()
                        setFragmentResult("hasta_eklendi", bundleOf("eklendi" to true))
                    findNavController().popBackStack()
                    binding.loadingProgressBar.visibility = View.GONE
                }
                is RandevuState.Error -> {
                        Snackbar.make(binding.root, "Hasta oluşturulamadı.", Snackbar.LENGTH_SHORT).show()
                    binding.kaydetBtn.isEnabled = true
                    binding.loadingProgressBar.visibility = View.GONE
                }
            }
        }

        binding.kaydetBtn.setOnClickListener{
            val selectedDurum = binding.randevuDurumuSpinner.selectedItem.toString()
            val dateTimeStr = "$selectedDate $selectedTime"
            val formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm")
            val localDateTime = LocalDateTime.parse(dateTimeStr.trim(), formatter)

            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            val formattedDate = localDateTime.format(dateFormatter)
            var yeniRandevu = Randevu(
                hasta = Hasta(
                    tcKimlikNo = binding.hastaTc.text.toString().trim(),
                    ad = binding.hastaAd.text.toString().trim(),
                    soyad = binding.hastaSoyad.text.toString().trim()
                ),
                doktor =  selectedDoktor!!,
                durum = selectedDurum,
                randevuTarihi = formattedDate // Formatted date'i gönderiyoruz
            )
            if (randevu == null) {
                viewModel.addRandevu(yeniRandevu)
            } else {
                randevu!!.id?.let { it1 -> viewModel.updateRandevu(id = it1,randevu= randevu!!) }
            }

        }

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