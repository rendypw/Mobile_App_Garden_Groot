package com.rendyanto.uasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class Masukan : AppCompatActivity() {
    lateinit var userDBHelper: DBHelperTanaman
    lateinit var inputid: EditText
    lateinit var inputukuran: EditText
    lateinit var inputjenis: EditText
    lateinit var inputharga: EditText
    lateinit var inputstok: EditText
    lateinit var btninput: Button
    lateinit var btnshow: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masukan)
        inputid = findViewById(R.id.id_tnmn)
        inputukuran = findViewById(R.id.uk_pot)
        inputjenis = findViewById(R.id.jenis_tnmn)
        inputharga = findViewById(R.id.harga)
        inputstok = findViewById(R.id.stok)
        btninput = findViewById(R.id.btn_input)
        btnshow = findViewById(R.id.btn_show)
        userDBHelper = DBHelperTanaman(this)
    }
    fun addData(view: View){
        var idin = inputid.text.toString()
        var ukin = inputukuran.text.toString()
        var jenisnin = inputjenis.text.toString()
        var hargain = inputharga.text.toString()
        var stokin = inputstok.text.toString()
        userDBHelper.insertData(idin, ukin, jenisnin, hargain,stokin)
        inputid.setText("")
        inputukuran.setText("")
        inputjenis.setText("")
        inputharga.setText("")
        inputstok.setText("")

    }
    fun showAll(view: View){
        var pindah = Intent(this, RvDb::class.java)
        startActivity(pindah)
    }
    }