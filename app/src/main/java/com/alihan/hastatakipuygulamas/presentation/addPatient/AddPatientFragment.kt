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
    private var mevcutHasta: Hasta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mevcutHasta = it.getParcelable("hasta") // Hasta sınıfı Parcelable olmalı
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPatientBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        if(mevcutHasta==null){
            var ad = binding.ad
            var soyad = binding.soyad
            var tc_no = binding.tcNo
            var dogum_tarihi = binding.dogumTarihi
            var email = binding.email
            var telefon = binding.telefon
            val fields = listOf(ad, soyad, tc_no, dogum_tarihi, email, telefon)

            fun checkFields() {
                binding.kaydetBtn.isEnabled = fields.all { it.text.toString().isNotBlank() } || mevcutHasta != null
            }

            fields.forEach { editText ->
                editText.addTextChangedListener {
                    checkFields()
                }
            }
        }else{
            binding.kaydetBtn.isEnabled=true
        }


        binding.erkekCheck.setOnClickListener {
            if (binding.kadinCheck.isChecked) {
                binding.kadinCheck.isChecked = false
            }
        }
        binding.kadinCheck.setOnClickListener {
            if (binding.erkekCheck.isChecked) {
                binding.erkekCheck.isChecked = false
            }
        }

        // Eğer mevcutHasta varsa, alanları doldur
        mevcutHasta?.let { hasta ->
            binding.ad.setText(hasta.ad)
            binding.soyad.setText(hasta.soyad)
            binding.tcNo.setText(hasta.tcKimlikNo)
            binding.dogumTarihi.setText(hasta.dogumTarihi)
            binding.email.setText(hasta.email)
            binding.telefon.setText(hasta.telefon)
            binding.adres.setText(hasta.adres)
            binding.acilDurumKisi.setText(hasta.acilDurumKisi)

            when (hasta.cinsiyet) {
                "Erkek" -> binding.erkekCheck.isChecked = true
                "Kadın" -> binding.kadinCheck.isChecked = true
                else -> {
                    binding.erkekCheck.isChecked = false
                    binding.kadinCheck.isChecked = false
                }
            }
            binding.kaydetBtn.text = "Güncelle"
        }



        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Kaydet butonuna tıklama işlemi
        binding.kaydetBtn.setOnClickListener {
            val yeniHasta = Hasta(
                id = mevcutHasta?.id,
                ad = binding.ad.text.toString(),
                soyad = binding.soyad.text.toString(),
                tcKimlikNo = binding.tcNo.text.toString(),
                cinsiyet = when {
                    binding.erkekCheck.isChecked -> "Erkek"
                    binding.kadinCheck.isChecked -> "Kadın"
                    else -> "Bilinmiyor"
                },
                dogumTarihi = binding.dogumTarihi.text.toString(),
                email = binding.email.text.toString(),
                telefon = binding.telefon.text.toString(),
                adres = binding.adres.text.toString(),
                acilDurumKisi = binding.acilDurumKisi.text.toString()
            )

            if (mevcutHasta == null) {
                viewModel.addPatient(yeniHasta)
            } else {
                mevcutHasta?.id?.let { it1 -> viewModel.updatePatient(it1,yeniHasta) }
            }
        }

        // ViewModel state gözlemi
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
