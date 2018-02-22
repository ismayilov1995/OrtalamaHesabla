package com.example.ismayil.ortalamahesabla

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_layout_ders.view.*

class MainActivity : AppCompatActivity() {
    private val dersler = arrayListOf<String>("Matematik","Fizika","Kimya","Qeyriselis Mentiq","Azerbaycan Dili")
    private var tumDerslerinBilgileri: ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hesablaBtnView()
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,dersler)
        Ders.setAdapter(adapter)


        btnAdd.setOnClickListener {
            if (!Ders.text.isNullOrEmpty()){
                val inflater = LayoutInflater.from(this)
                val yeniDersView = inflater.inflate(R.layout.yeni_layout_ders,null)
                val dersAd = Ders.text.toString()
                val dersKredi = spKredi.selectedItem.toString()
                val dersNot = spNot.selectedItem.toString()

                yeniDersView.yeniDers.setText(dersAd)
                yeniDersView.yeniSpKredi.setSelection(spinnerDegerinIxTap(spKredi,dersKredi))
                yeniDersView.yeniSpNot.setSelection(spinnerDegerinIxTap(spNot,dersNot))

                yeniDersView.btnSil.setOnClickListener {
                    rootLayout.removeView(yeniDersView)
                    hesablaBtnView()
                }


                rootLayout.addView(yeniDersView)
                hesablaBtnView()
                sifirla()
            }else{
                FancyToast.makeText(this,"Dersin Adini Yazin",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
            }

        }



    }


    fun sifirla(){
        Ders.setText("")
        spKredi.setSelection(0)
        spNot.setSelection(0)
    }

    fun hesablaBtnView(){
        if (rootLayout.childCount == 0){
            btnOrtalamaHesabla.visibility = View.INVISIBLE
        }else btnOrtalamaHesabla.visibility = View.VISIBLE
    }

    fun spinnerDegerinIxTap(spinner:Spinner,searchingVal:String) : Int{
        var index = 0
        for (i in 0..spinner.count){
            if (spinner.getItemAtPosition(i).toString() == searchingVal){
                index = i
                break
            }
        }
        return index
    }

    fun hesabla(view: View){

        var toplamNot = 0.0
        var toplamKredi = 0.0

        for (i in 0..rootLayout.childCount-1){
            var tekSatir = rootLayout.getChildAt(i)
            var geciciDers = Dersler(tekSatir.yeniDers.text.toString(),
                    ((tekSatir.yeniSpKredi.selectedItemPosition)+1).toString(),
                    tekSatir.yeniSpNot.selectedItem.toString())
            tumDerslerinBilgileri.add(geciciDers)
        }
        for (oankiDers in tumDerslerinBilgileri){

            toplamNot += harfiNotaCevir(oankiDers.dersNotu)*(oankiDers.dersKredi.toDouble())
            toplamKredi += oankiDers.dersKredi.toDouble()

        }

        FancyToast.makeText(this,"Ortalama: ${toplamNot/toplamKredi}",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show()
        tumDerslerinBilgileri.clear()
    }

    fun harfiNotaCevir(gelenNotHarf:String):Double{

        var deger = 0.0

        when(gelenNotHarf){

            "AA" -> deger = 4.0
            "BA" -> deger = 3.5
            "BB" -> deger = 3.0
            "CB" -> deger = 2.5
            "CC" -> deger = 2.0
            "DC" -> deger = 1.5
            "DD" -> deger = 1.0
            "FF" -> deger = 0.0

        }
        return deger
    }


}
