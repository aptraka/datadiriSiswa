package com.aptraka14.datadirisiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aptraka14.datadirisiswa.model.dataModel

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val nama = findViewById<TextView>(R.id.tv_name)
        val nohp = findViewById<TextView>(R.id.tv_nohp)
        val gender = findViewById<TextView>(R.id.tv_gender)
        val jenjang = findViewById<TextView>(R.id.tv_jenjang)
        val hobi = findViewById<TextView>(R.id.tv_hobi)
        val alamat = findViewById<TextView>(R.id.tv_alamat)

        val getNama: String? = intent.getStringExtra("nama")
        val getNohp: String? = intent.getStringExtra("hp")
        val getGender: String? = intent.getStringExtra("gender")
        val getJenjang: String? = intent.getStringExtra("jenjang")
        val getHobi: String? = intent.getStringExtra("hobi")
        val getAlamat: String? = intent.getStringExtra("alamat")

        nama.text = getNama.toString()
        nohp.text = getNohp.toString()
        gender.text = getGender.toString()
        jenjang.text = getJenjang.toString()
        hobi.text = getHobi.toString()
        alamat.text = getAlamat.toString()


    }
}