package com.example.e_agro_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.e_agro_4.databinding.ActivityMainBinding

class Notificationactivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textview3:TextView=findViewById(R.id.textView3)
        val textview6:TextView=findViewById(R.id.textView6)
        val imageview2:ImageView=findViewById(R.id.imageView2)

        val offertext=intent.getStringExtra("offertext")
        val offerdesc=intent.getStringExtra("offerdesc")
        val imageId=intent.getIntExtra("imageId",R.drawable.logo)

        textview3.text=offertext
        textview6.text=offerdesc
        imageview2.setImageResource(imageId)
    }
}