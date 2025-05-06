package com.alihan.hastatakipuygulamas.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.ItemPatientBinding
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListFragmentDirections

class PatientAdapter : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    private var hastaList: List<Hasta> = emptyList()

    fun setData(newList: List<Hasta>) {
        this.hastaList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemPatientBinding>(
            inflater, R.layout.item_patient, parent, false
        )
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val hasta = hastaList[position]
        holder.view.hasta = hasta
        holder.view.itemLy.setOnClickListener{
            println("aaaa")
            val action = PatientListFragmentDirections
                .actionPatientListFragmentToPatientInfoFragment(hasta)
            findNavController(holder.itemView).navigate(action)


        }
    }

    override fun getItemCount(): Int {
        return hastaList.size
    }

    class PatientViewHolder(var view: ItemPatientBinding) : RecyclerView.ViewHolder(view.root)


}
