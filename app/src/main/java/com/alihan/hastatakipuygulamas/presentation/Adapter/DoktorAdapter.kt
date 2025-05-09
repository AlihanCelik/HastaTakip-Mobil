package com.alihan.hastatakipuygulamas.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alihan.hastatakipuygulamas.R
import com.alihan.hastatakipuygulamas.data.model.Doktor
import com.alihan.hastatakipuygulamas.databinding.ItemDoktorBinding
import com.alihan.hastatakipuygulamas.presentation.doktorList.DoktorListFragmentDirections

class DoktorAdapter : RecyclerView.Adapter<DoktorAdapter.DoktorViewHolder>() {

    private var doktorList: List<Doktor> = emptyList()

    fun setData(newList: List<Doktor>) {
        this.doktorList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DoktorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDoktorBinding>(
            inflater, R.layout.item_doktor, parent, false
        )
        return DoktorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoktorViewHolder, position: Int) {
        val doktor = doktorList[position]
        holder.view.doktor = doktor
        holder.view.itemLy.setOnClickListener {
            val action = DoktorListFragmentDirections
                .actionDoktorListFragmentToDoktorInfoFragment(doktor)
            findNavController(holder.itemView).navigate(action)


        }
    }

    override fun getItemCount(): Int {
        return doktorList.size
    }

    class DoktorViewHolder(var view: ItemDoktorBinding) : RecyclerView.ViewHolder(view.root)
}