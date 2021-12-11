package com.example.e_agro_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.e_agro_4.databinding.ActivityMainBinding

class Notifications : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
            //listview notfictaions code
            val arrayAdapter: ArrayAdapter<*>
                val notificationlist=arrayOf("50% off on your first orders","Free home delivery on the purchase of agriculture equiments",
                    "Upto 40% discounts on Fertilizers","Seeds of all crops at the most affordable price")

                //acessing the listview
                var mListView=findViewById<ListView>(R.id.listview)
                arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,notificationlist)
                mListView.adapter=arrayAdapter
                mListView.setOnItemClickListener { adapterView, view, i, id ->
                    Toast.makeText(this,"Item clicked "+notificationlist[i],Toast.LENGTH_SHORT).show()
                }

//        listview.setOnItemClickListener { parent, view, position, id ->
//
//            val offertext=offertext[position]
//            val offerdesc=offerdesc[position]
//            val imageId=imageId[position]
//            val i= Intent(this,Login::class.java)
//            i.putExtra("offertext",offertext)
//            i.putExtra("offerdesc",offerdesc)
//            i.putExtra("imageId",imageId)
//            startActivity(i)
//        }
//
//




    }
}