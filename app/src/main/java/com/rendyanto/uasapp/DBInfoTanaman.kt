package com.rendyanto.uasapp

import android.provider.BaseColumns

object DBInfoTanaman {
    class UserInput: BaseColumns {
        companion object {
        val TABLE_NAME = "tanaman"
        val COL_ID = "idtnmn"
        val COL_UKURAN = "ukpot"
        val COL_JENIS = "jenistnmn"
        val COL_HARGA = "hargatnmn"
        val COL_STOK = "stoktnmn"

    }
}
}