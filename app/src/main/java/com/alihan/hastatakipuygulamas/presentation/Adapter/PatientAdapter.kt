package com.alihan.hastatakipuygulamas.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.ItemPatientBinding

class PatientAdapter(val hastaList: LiveData<List<Hasta>>): RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemPatientBinding>(inflater,
            R.layout.item_patient,parent,false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.view.hasta = hastaList.value?.get(position)



    }

    class PatientViewHolder(var view: ItemPatientBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun getItemCount(): Int {
        return hastaList.value!!.size
    }
}