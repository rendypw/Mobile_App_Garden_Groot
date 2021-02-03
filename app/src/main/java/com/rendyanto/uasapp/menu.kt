package com.rendyanto.uasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnLogout: Button = findViewById(R.id.btn_logout)
        val btnBelanja: Button = findViewById(R.id.btnbelanja)
        val btnProfil: Button = findViewById(R.id.btnprofil)
        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        btnLogout.setOnClickListener {
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.putString("Status", "Off")
            editSavedLogin.commit()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btnBelanja.setOnClickListener {
            startActivity(Intent(this,Masukan::class.java))
        }
        btnProfil.setOnClickListener {
            startActivity(Intent(this,ProfilOwners::class.java))
        }
    }
}