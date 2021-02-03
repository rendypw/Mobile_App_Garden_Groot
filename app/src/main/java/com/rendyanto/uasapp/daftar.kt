package com.rendyanto.uasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class daftar : AppCompatActivity() {
    lateinit var enama: EditText
    lateinit var epassword: EditText
    lateinit var eemail: EditText
    lateinit var elamat: EditText
    lateinit var enomor: EditText
    lateinit var btnregister: Button
    lateinit var btncancel: Button
    lateinit var userDBHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        enama = findViewById(R.id.edit_nama)
        epassword = findViewById(R.id.edit_pass)
        eemail= findViewById(R.id.edit_email)
        elamat = findViewById(R.id.edit_alamat)
        enomor = findViewById(R.id.edit_nomor)
        btnregister = findViewById(R.id.btn_daftar)
        btncancel = findViewById(R.id.btn_cancel)
        userDBHelper = DBHelper(this)
    }
    fun registerme(view: View){
        var inama = enama.text.toString()
        var ipassword = epassword.text.toString()
        var iemail = eemail.text.toString()
        var ialamat = elamat.text.toString()
        var inomor = enomor.text.toString()
        var cekuser = userDBHelper.cekUser(iemail)
        var status = "Gagal"
        if (cekuser =="0") {
            userDBHelper.RegisterUser(inama, ipassword, iemail, ialamat, inomor)
            status = "Sukses"
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val toast: Toast = Toast.makeText(applicationContext,
                status, Toast.LENGTH_SHORT)
        toast.show()
    }
    fun cancelme(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
}

