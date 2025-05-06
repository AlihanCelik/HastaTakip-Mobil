package com.alihan.hastatakipuygulamas.presentation.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Hasta
import com.alihan.hastatakipuygulamas.databinding.ItemPatientBinding

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
        holder.view.hasta = hastaList[position]
        holder.itemView.setOnClickListener{
            val action = PatientListFragmentDirections
                .actionPatientListFragmentToPatientInfoFragment(hastaList[position])
            findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return hastaList.size
    }

    class PatientViewHolder(var view: ItemPatientBinding) : RecyclerView.ViewHolder(view.root)


}
