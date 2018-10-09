package com.kuduspot.ilkuygulama

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.tek_satir_horizontal.view.*

class BurcAdapter(tumBurclar:ArrayList<Burc>): RecyclerView.Adapter<BurcAdapter.BurcViewHolderHor>() {

    var burclar=tumBurclar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurcViewHolderHor {

        var inflater=LayoutInflater.from(parent.context)
        //var tekSatirBurc=inflater.inflate(R.layout.tek_satir_burc,parent,false)
        var tekSatirBurc=inflater.inflate(R.layout.tek_satir_horizontal,parent,false)


        return BurcViewHolderHor(tekSatirBurc)
    }

    //İlk olarak liste boyutu oluşturulacak.
    override fun getItemCount(): Int {
        return burclar.size
    }
    //Değişenlere veri atama
    override fun onBindViewHolder(holder: BurcViewHolderHor, position: Int) {

        var curBurcBilgisi=burclar.get(position)

        holder?.setData(curBurcBilgisi,position)

    }

    class BurcViewHolderHor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tekBurcHor=itemView as CardView

        var burcIkon=tekBurcHor.imgBurcHor
        var burcAdi=tekBurcHor.tvBurcAdiHor


        fun setData(curBurcBilgisi: Burc, position: Int) {
            burcAdi.text=curBurcBilgisi.baslik
            burcIkon.setImageResource(curBurcBilgisi.resim)


            tekBurcHor.setOnClickListener{

                Toast.makeText(tekBurcHor.context,curBurcBilgisi.aciklama,Toast.LENGTH_LONG).show()
            }
        }
    }
}


