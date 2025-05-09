package com.alihan.hastatakipuygulamas.presentation.addPatient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.FragmentAddPatientBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentPatientListBinding
import com.alihan.hastatakipuygulamas.presentation.Adapter.PatientAdapter
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListViewModel
import com.google.android.material.snackbar.Snackbar
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

        var ad=binding.ad
        var soyad=binding.soyad
        var tc_no=binding.tcNo
        var dogum_tarihi=binding.dogumTarihi
        var email=binding.email
        var telefon=binding.telefon
        binding.erkekCheck.setOnClickListener {
            if(binding.kadinCheck.isChecked){
                binding.kadinCheck.isChecked=false
            }
        }
        binding.kadinCheck.setOnClickListener {
            if(binding.erkekCheck.isChecked){
                binding.erkekCheck.isChecked=false
            }
        }
        val fields = listOf(ad, soyad, tc_no, dogum_tarihi, email, telefon)

        fun checkFields() {
            binding.kaydetBtn.isEnabled = fields.all { it.text.toString().isNotBlank() }
        }

        fields.forEach { editText ->
            editText.addTextChangedListener {
                checkFields()
            }
        }
        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.kaydetBtn.setOnClickListener {
            val ad = binding.ad.text.toString()
            val soyad = binding.soyad.text.toString()
            val tc = binding.tcNo.text.toString()
            val dogumTarihi = binding.dogumTarihi.text.toString()
            val email=binding.email.text.toString()
            val telefon=binding.telefon.text.toString()
            val adres=binding.adres.text.toString()
            val acilDurumKisi=binding.acilDurumKisi.text.toString()
            var cinsiyet: String? =null
            if(binding.erkekCheck.isChecked){
                cinsiyet="Erkek"
            }else if(binding.kadinCheck.isChecked){
                cinsiyet="Kadın"
            }else{
                cinsiyet="Bilinmiyor"
            }
            val yeniHasta = Hasta(
                ad = ad,
                soyad = soyad,
                tcKimlikNo = tc,
                cinsiyet = cinsiyet,
                dogumTarihi = dogumTarihi,
                email = email,
                telefon = telefon,
                adres = adres,
                acilDurumKisi = acilDurumKisi
            )
            viewModel.addPatient(yeniHasta)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PatientListState.Loading -> {
                    binding.kaydetBtn.isEnabled = false
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                is PatientListState.Success -> {
                    Snackbar.make(binding.root, "Hasta başarıyla eklendi", Snackbar.LENGTH_SHORT).show()
                    setFragmentResult("hasta_eklendi", bundleOf("eklendi" to true))
                    findNavController().popBackStack()
                    binding.loadingProgressBar.visibility = View.GONE
                }
                is PatientListState.Error -> {
                    Snackbar.make(binding.root, "Hasta oluşturulamadı", Snackbar.LENGTH_SHORT).show()
                    binding.kaydetBtn.isEnabled = true
                    binding.loadingProgressBar.visibility = View.GONE
                }
            }
        }
        return binding.root
    }

}