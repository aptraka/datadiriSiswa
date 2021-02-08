package com.aptraka14.datadirisiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.aptraka14.datadirisiswa.model.dataModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        viewData()
        super.onResume()
    }

    fun viewData() {
        val dbHandler: DatabaseHelper = DatabaseHelper(this)

        val dataList: List<dataModel> = dbHandler.viewData()

        val arraydataID = Array<String>(dataList.size) { "0" }
        val arraydataName = Array<String>(dataList.size) { "null" }
        val arraydataGender = Array<String>(dataList.size) { "null" }
        val arraydataJenjang = Array<String>(dataList.size) { "null" }
        val arraydataNohp = Array<String>(dataList.size) { "null" }
        var index = 0

        for (e in dataList) {
            arraydataID[index] = e.id.toString()
            arraydataName[index] = e.nama.toString()
            arraydataGender[index] = e.gender.toString()
            arraydataJenjang[index] = e.jenjang.toString()
            arraydataNohp[index] = e.hp.toString()
            index++
        }
        val myAdapter =
            AdapterList(this, arraydataName, arraydataGender, arraydataJenjang, arraydataNohp)
        val lv_list = findViewById<ListView>(R.id.lv_list)
        lv_list.adapter = myAdapter
        lv_list.setOnItemClickListener { adapterView, view, i, l ->
            val model: dataModel = dataList.get(i)
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("nama", model.nama)
            intent.putExtra("hp", model.hp)
            intent.putExtra("jenjang", model.jenjang)
            intent.putExtra("hobi", model.hobi)
            intent.putExtra("alamat", model.alamat)
            intent.putExtra("gender", model.gender)
            startActivity(intent)
            Toast.makeText(this,model.nama.toString(),Toast.LENGTH_SHORT).show()

        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                startActivity(Intent(this@MainActivity, InputDataActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


