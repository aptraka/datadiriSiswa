package com.aptraka14.datadirisiswa

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdapterList(
    private val context: Activity,
    private val name: Array<String>,
    private val gender: Array<String>,
    private val jenjang: Array<String>,
    private val noHP: Array<String>
) : ArrayAdapter<String>(context, R.layout.item_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_list, null, true)

        val nameText = rowView.findViewById(R.id.tv_name) as TextView
        val genderText = rowView.findViewById(R.id.tv_gender) as TextView
        val nohpText = rowView.findViewById(R.id.tv_nohp) as TextView
        val jenjangText = rowView.findViewById(R.id.tv_jenjang) as TextView

        nameText.text = "${name[position]}"
        genderText.text = "${gender[position]}"
        nohpText.text = "${noHP[position]}"
        jenjangText.text = "${jenjang[position]}"

        return rowView
    }
}