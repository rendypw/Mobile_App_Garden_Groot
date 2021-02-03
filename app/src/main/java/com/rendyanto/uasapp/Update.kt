package com.rendyanto.uasapp

import android.content.Intent
import android.nfc.tech.NfcV
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class Update : AppCompatActivity() {
    lateinit var userDBHelper: DBHelperTanaman
    lateinit var inputid: EditText
    lateinit var inputukuran: EditText
    lateinit var inputjenis: EditText
    lateinit var inputharga: EditText
    lateinit var inputstok: EditText
    lateinit var idg: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        inputid = findViewById(R.id.id_tnmnu)
        inputukuran = findViewById(R.id.uk_potu)
        inputjenis = findViewById(R.id.jenis_tnmnu)
        inputharga = findViewById(R.id.hargau)
        inputstok = findViewById(R.id.stoku)
        userDBHelper = DBHelperTanaman(this)
        val bundle= intent.extras
        if (bundle != null){
            idg = bundle.getString("idk").toString()
            inputid.setText(bundle.getString("idk"))
            inputukuran.setText(bundle.getString("ukpotk"))
            inputjenis.setText(bundle.getString("jenisk"))
            inputharga.setText(bundle.getString("hargak"))
            inputstok.setText(bundle.getString("stokk"))
        }
    }
    fun cancelUpAc(v: View){
        val kembali =Intent (this, RvDb::class.java)
        startActivity(kembali)

    }
    fun updateUpAc(v: View){
        var idin = inputid.text.toString()
        var ukurin = inputukuran.text.toString()
        var jenisin = inputjenis.text.toString()
        var hargain = inputharga.text.toString()
        var stokin = inputstok.text.toString()
        userDBHelper.updateTanaman(idin,ukurin,jenisin,hargain,stokin)
        val kembali = Intent(this, RvDb::class.java)
        startActivity(kembali)
    }
}