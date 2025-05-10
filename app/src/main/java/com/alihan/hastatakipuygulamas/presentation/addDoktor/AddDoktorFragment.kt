package com.alihan.hastatakipuygulamas.presentation.addDoktor

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
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.FragmentAddDoktorBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentAddPatientBinding
import com.alihan.hastatakipuygulamas.databinding.FragmentDoktorListBinding
import com.alihan.hastatakipuygulamas.presentation.addPatient.AddPatientViewModel
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListState
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDoktorFragment : Fragment() {
    private var _binding: FragmentAddDoktorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddDoktorViewModel by viewModels()
    private var mevcutDoktor: Doktor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mevcutDoktor = it.getParcelable("doktor")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddDoktorBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        if(mevcutDoktor==null){
            var ad = binding.ad
            var soyad = binding.soyad
            var email = binding.email
            var telefon = binding.telefon
            var brans = binding.bran
            val fields = listOf(ad, soyad, brans, email, telefon)

            fun checkFields() {
                binding.kaydetBtn.isEnabled = fields.all { it.text.toString().isNotBlank() } || mevcutDoktor != null
            }

            fields.forEach { editText ->
                editText.addTextChangedListener {
                    checkFields()
                }
            }
        }else{
            binding.kaydetBtn.isEnabled=true
        }


        mevcutDoktor?.let { doktor ->
            binding.ad.setText(doktor.ad)
            binding.soyad.setText(doktor.soyad)
            binding.email.setText(doktor.email)
            binding.telefon.setText(doktor.telefon)
            binding.bran.setText(doktor.branş)
            binding.kaydetBtn.text = "Güncelle"
        }



        binding.geriButton.setOnClickListener {
            findNavController().popBackStack()
        }

        var yeniDoktor: Doktor? = null
        binding.kaydetBtn.setOnClickListener {
            yeniDoktor = Doktor(
                id = mevcutDoktor?.id,
                ad = binding.ad.text.toString(),
                soyad = binding.soyad.text.toString(),
                branş = binding.bran.text.toString(),
                email = binding.email.text.toString(),
                telefon = binding.telefon.text.toString()
            )

            if (mevcutDoktor == null) {
                viewModel.addDoktor(yeniDoktor!!)
            } else {
                mevcutDoktor?.id?.let { it1 -> viewModel.updateDoktor(it1,yeniDoktor!!) }
            }
        }


        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DoktorListState.Loading -> {
                    binding.kaydetBtn.isEnabled = false
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                is DoktorListState.Success -> {
                    if (mevcutDoktor==null){
                        Snackbar.make(binding.root, "Doktor başarıyla eklendi", Snackbar.LENGTH_SHORT).show()
                        setFragmentResult("doktor_eklendi", bundleOf("eklendi" to true))
                    }else{
                        Snackbar.make(binding.root, "Doktor başarıyla güncellendi", Snackbar.LENGTH_SHORT).show()
                        setFragmentResult("doktor_guncellendi", bundleOf("doktor" to yeniDoktor))
                    }
                    findNavController().popBackStack()
                    binding.loadingProgressBar.visibility = View.GONE
                }
                is DoktorListState.Error -> {
                    if(mevcutDoktor==null){
                        Snackbar.make(binding.root, "Doktor oluşturulamadı.", Snackbar.LENGTH_SHORT).show()
                    }else{
                        Snackbar.make(binding.root, "Doktor güncellenemedi.", Snackbar.LENGTH_SHORT).show()
                    }
                    binding.kaydetBtn.isEnabled = true
                    binding.loadingProgressBar.visibility = View.GONE
                }
            }
        }
        return binding.root
    }


}