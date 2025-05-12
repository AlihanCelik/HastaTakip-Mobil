package com.alihan.hastatakipuygulamas.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Randevu
import com.alihan.hastatakipuygulamas.databinding.ItemRandevuBinding
import com.alihan.hastatakipuygulamas.presentation.patientList.PatientListFragmentDirections
import com.alihan.hastatakipuygulamas.presentation.randevuList.RandevuListFragmentDirections

class RandevuAdapter : RecyclerView.Adapter<RandevuAdapter.RandevuViewHolder>() {

    private var randevuList: List<Randevu> = emptyList()

    fun setData(newList: List<Randevu>) {
        this.randevuList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RandevuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemRandevuBinding>(
            inflater, R.layout.item_randevu, parent, false
        )
        return RandevuViewHolder(view)
    }

    override fun onBindViewHolder(holder: RandevuViewHolder, position: Int) {
        val randevu = randevuList[position]
        holder.view.randevu = randevu
        holder.view.itemLy.setOnClickListener {
            val action = RandevuListFragmentDirections.actionRandevuListFragmentToRandevuFragment(hasta = null, randevu = randevu)
            findNavController(holder.itemView).navigate(action)


        }
    }

    override fun getItemCount(): Int {
        return randevuList.size
    }

    class RandevuViewHolder(var view: ItemRandevuBinding) : RecyclerView.ViewHolder(view.root)
}