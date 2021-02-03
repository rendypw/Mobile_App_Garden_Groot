package com.rendyanto.uasapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DbAdapterTanaman (private val listDataku: ArrayList<DbModelTanaman>): RecyclerView.Adapter<DbAdapterTanaman.CardViewHolder>() {
        inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
            var tvidtnmn: TextView = itemV.findViewById(R.id.tv_idtnmn)
            var tvukpot: TextView = itemV.findViewById(R.id.tv_ukpot)
            var tvjenis: TextView = itemV.findViewById(R.id.tv_jenis)
            var tvharga: TextView = itemV.findViewById(R.id.tv_harga)
            var tvstok: TextView = itemV.findViewById(R.id.tv_stok)
            var btnupdate: Button = itemV. findViewById(R.id.btn_update)
            var btndelete: Button = itemV. findViewById(R.id.btn_delete)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
            return CardViewHolder(view)
        }

        override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
            val dataku = listDataku[position]
            holder.tvidtnmn.text = dataku.id
            holder.tvukpot.text = dataku.ukuran
            holder.tvjenis.text = dataku.jenis
            holder.tvharga.text = dataku.harga
            holder.tvstok.text = dataku.stok

            holder.btndelete.setOnClickListener{
                var adapterDbHelper: DBHelperTanaman
                adapterDbHelper = DBHelperTanaman(holder.itemView.context)
                adapterDbHelper.deleteTanaman(dataku.id)
                listDataku.removeAt(position)
                notifyDataSetChanged()
            }

            holder.btnupdate.setOnClickListener{
                val pindahUpAc = Intent(holder.itemView.context,Update::class.java)
                val bundle = Bundle()
                bundle.putString("idk", dataku.id)
                bundle.putString("ukpotk", dataku.ukuran)
                bundle.putString("jenisk", dataku.jenis)
                bundle.putString("hargak", dataku.harga)
                bundle.putString("stokk", dataku.stok)
                pindahUpAc.putExtras(bundle)
                holder.itemView.context.startActivity(pindahUpAc)
            }
        }

        override fun getItemCount(): Int {
            return listDataku.size
        }
    }
