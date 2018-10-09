package com.kuduspot.ilkuygulama

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.time.Instant
import java.util.*

class MainActivity : AppCompatActivity() {

    val tumBurclar=ArrayList<Burc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var takvim=Calendar.getInstance()
        var curDay=takvim.get(Calendar.DAY_OF_MONTH)
        var curMonth=takvim.get(Calendar.MONTH)
        var curYear="2017"

        var apiTarih=curYear+"-"+curMonth+"-"+curDay

        val url="https://www.gunlukburcun.com/api?tarih="+apiTarih+"&format=json"

        val tarihler= resources.getStringArray(R.array.tarihler)
        Log.e("TARİHLER",tarihler.size.toString())


        var jsonRequest = JsonArrayRequest(Request.Method.GET,url,null,object:Response.Listener<JSONArray>{
            override fun onResponse(response: JSONArray?) {

                for(i in 0..response!!.length()-1) {
                    var jsonBurc = response.getJSONObject(i)
                    var burcYorum=jsonBurc.getString("yorum")
                    var burcAdi=jsonBurc.getString( "burc")
                    var burcImg=jsonBurc.getString("burc_sef")

                    var resId=resources.getIdentifier(burcImg,"drawable",packageName)
                    //Log.e("LogDeneme", "Burçlar " + resId +" "+R.drawable.koc )

                    var burcEkle=Burc(burcAdi,burcYorum,resId,tarihler.get(i).toString())
                    tumBurclar.add(burcEkle)
                    //Log.e("SAMET",tumBurclar[i].tarih+" "+tumBurclar[i].baslik+" "+tumBurclar[i].resim.toString()+" "+tumBurclar[i].aciklama)


                    var myAdapter=BurcAdapter(this@MainActivity.tumBurclar)
                    rvBurclar.adapter=myAdapter

                    var lineerLayoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    rvBurclar.layoutManager=lineerLayoutManager
                }

            }
        },object:Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {

            }
        })

        MySingleton.getInstance(this)?.addToRequestQueue(jsonRequest)
    }
}
