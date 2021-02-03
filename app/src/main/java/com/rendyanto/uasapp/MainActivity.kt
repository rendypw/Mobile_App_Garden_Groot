package com.rendyanto.uasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnRegister: Button = findViewById(R.id.btnReg)
        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        if (savedLogin.getString("Status", "Off")=="On"){
            startActivity(Intent(this, Splash::class.java))
        }
        val editUsername: EditText = findViewById(R.id.editext_username)
        val editPassword: EditText = findViewById(R.id.editext_pass)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val userDBHelper = DBHelper(this)
        btnLogin.setOnClickListener {
            var emailku = editUsername.text.toString()
            var passku = editPassword.text.toString()
            var cekLogin = userDBHelper.cekLogin(emailku, passku)
            if(cekLogin=="1"){
                editSavedLogin.putString("Email", emailku)
                editSavedLogin.putString("Password", passku)
                editSavedLogin.putString("Status", "On")
                editSavedLogin.commit()
                val intent = Intent(this, Splash::class.java)
                startActivity(intent)
            } else {
                val toast: Toast = Toast.makeText(applicationContext,
                        "Gagal Login", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        btnRegister.setOnClickListener {
            val intent = Intent(this, daftar::class.java)
            startActivity(intent)
    }
}}