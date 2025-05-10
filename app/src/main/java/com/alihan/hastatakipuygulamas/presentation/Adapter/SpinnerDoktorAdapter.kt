package com.alihan.hastatakipuygulamas.presentation.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.alihan.hastatakipuygulamas.data.model.Doktor

class SpinnerDoktorAdapter(
    context: Context,
    private val doktorList: List<Doktor>
) : ArrayAdapter<Doktor>(context, android.R.layout.simple_spinner_item, doktorList) {

    override fun getCount(): Int = doktorList.size

    override fun getItem(position: Int): Doktor = doktorList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val doktor = getItem(position)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = "${doktor.ad} ${doktor.soyad} (${doktor.branş})"
        textView.setPadding(20, 35, 20, 35)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val doktor = getItem(position)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = "${doktor.ad} ${doktor.soyad} (${doktor.branş})"
        textView.setPadding(20, 35, 20, 35)
        return view
    }
}
