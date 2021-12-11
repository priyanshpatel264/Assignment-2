package com.example.e_agro_4

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Seeds : AppCompatActivity() {
    lateinit var buttonbuy: Button

    private val CHANNEL_ID = "channel_01"
    private val notificationid = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seeds)

        buttonbuy=findViewById(R.id.buttonbuy)
        buttonbuy.setOnClickListener{
            Toast.makeText(this,"Pumpkin seeds Buyed Successfully !", Toast.LENGTH_SHORT).show()
            sendNotification("Buyed Successfully", "Pumpkin seeds Buyed Successfully !")
        }
    }
    private fun createnotificationchannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Sample notification"
            val descriptiontext = " notification desc example"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptiontext
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(title: String, descnotification: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingintent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.logo)
        val bitmaplargeicon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.logo)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.s2)
                .setContentTitle(title)
                .setContentText(descnotification)
                .setLargeIcon(bitmaplargeicon)
                .setStyle(NotificationCompat.BigTextStyle().bigText(descnotification))
                .setContentIntent(pendingintent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(notificationid, builder.build())
        }

    }
}