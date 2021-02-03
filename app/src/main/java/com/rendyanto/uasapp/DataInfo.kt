package com.rendyanto.uasapp

import android.provider.BaseColumns

object DataInfo {
    class UserTable: BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_NAMA = "username"
            val COL_PASS = "pass"
            val COL_EMAIL = "email"
            val COL_ALAMAT = "alamat"
            val COL_NOHP = "nohp"
            val COL_JUMLAH = "jumlah"
        }
    }
}