package com.aptraka14.datadirisiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.aptraka14.datadirisiswa.model.dataModel

class InputDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)
    }

    fun saveData() {
        val etName = findViewById<EditText>(R.id.et_nama)
        val etNohp = findViewById<EditText>(R.id.et_nohp)
        val rgGender = findViewById<RadioGroup>(R.id.rg_gender)
        val spJenjang = findViewById<Spinner>(R.id.spinner_jenjang)
        val cbMembaca = findViewById<CheckBox>(R.id.cb_membaca)
        val cbMenulis = findViewById<CheckBox>(R.id.cb_menulis)
        val cbMenggambar = findViewById<CheckBox>(R.id.cb_menggambar)
        val etAlamat = findViewById<EditText>(R.id.et_alamat)


        val nama = etName.text.toString()
        val noHp = etNohp.text.toString()
        val checkGender: Int = rgGender.checkedRadioButtonId
        var gender = ""
        if (checkGender == R.id.rb_pria) {
            gender = "Pria"
        } else if (checkGender == R.id.rb_wanita) {
            gender = "Wanita"
        }

        var hobi = ""
        if (cbMembaca.isChecked) {
            hobi += "Membaca, "
        }
        if (cbMenggambar.isChecked) {
            hobi += "Menggambar, "
        }
        if (cbMenulis.isChecked) {
            hobi += "Menulis "
        }
        val jenjang = spJenjang.selectedItem.toString()
        val alamat = etAlamat.text.toString()
        val databaseHelper: DatabaseHelper = DatabaseHelper(this)
        if (etName.text.equals("") || etNohp.text.equals("") || etAlamat.text.equals("")) {
            Toast.makeText(this, "Harap isi terlebih dahulu", Toast.LENGTH_SHORT).show()
        } else {
            val status =
                databaseHelper.addData(dataModel(null, nama, noHp, gender, jenjang, hobi, alamat))
            if (status > -1) {
                Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@InputDataActivity, MainActivity::class.java))
                finish()
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
               saveData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}