package com.example.e_agro_4

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_agro_4.product_slider.CategoryItem
import com.example.e_agro_4.product_slider.allcategory
import com.example.e_agro_4.product_slider.mainrecycleradapter
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var catimg1:ImageView
    lateinit var catimg2:ImageView
    lateinit var catimg3:ImageView

    private var mainCategoryRecycler:RecyclerView?=null
    private var mainRecyclerAdapter: mainrecycleradapter?=null


    private val CHANNEL_ID="channel_01"
    private val notificationid=101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createnotificationchannel()
        catimg1=findViewById(R.id.catimg1)
        catimg2=findViewById(R.id.catimg2)
        catimg3=findViewById(R.id.catimg3)
        catimg1.setOnClickListener{
            val intent: Intent =Intent(this,Fertilizers::class.java)
            startActivity(intent)
        }
        catimg2.setOnClickListener{
            val intent: Intent =Intent(this,Seeds::class.java)
            startActivity(intent)
        }
        catimg3.setOnClickListener{
            val intent: Intent =Intent(this,Tools::class.java)
            startActivity(intent)
        }
        //recycler view code

        //firstimage category
        val categoryItemList:MutableList<CategoryItem> = ArrayList()
        categoryItemList.add(CategoryItem(1,R.drawable.f1,"Dap","$10.00"))
        categoryItemList.add(CategoryItem(1,R.drawable.f2,"Urea","$5.50"))
        categoryItemList.add(CategoryItem(1,R.drawable.f3,"All Purpose","$15.45"))
        categoryItemList.add(CategoryItem(1,R.drawable.f4,"Super Growth","$12.50"))
        categoryItemList.add(CategoryItem(1,R.drawable.f5,"Organic","$20.85"))
        categoryItemList.add(CategoryItem(1,R.drawable.f6,"All Natural","$30.00"))

        //2ndimage category
        val categoryItemList2:MutableList<CategoryItem> = ArrayList()
        categoryItemList2.add(CategoryItem(1,R.drawable.s1,"Cashew Seed","$3.00"))
        categoryItemList2.add(CategoryItem(1,R.drawable.s2,"Pumpkin Seed","$5.00"))
        categoryItemList2.add(CategoryItem(1,R.drawable.s3,"Sunflower Seed","$6.70"))
        categoryItemList2.add(CategoryItem(1,R.drawable.s4,"Apple Seed","$5.55"))
        categoryItemList2.add(CategoryItem(1,R.drawable.s5,"Red Beans","$11.35"))
        categoryItemList2.add(CategoryItem(1,R.drawable.s6,"Basil Seed","$10.00"))

        //firstimage category
        val categoryItemList3:MutableList<CategoryItem> = ArrayList()
        categoryItemList3.add(CategoryItem(1,R.drawable.t1,"Drawer Saw","$10.00"))
        categoryItemList3.add(CategoryItem(1,R.drawable.t2,"Rounded Axe","$8.00"))
        categoryItemList3.add(CategoryItem(1,R.drawable.t3,"Saw","$7.99"))
        categoryItemList3.add(CategoryItem(1,R.drawable.t4,"Digger","$12.50"))
        categoryItemList3.add(CategoryItem(1,R.drawable.t5,"Manual Plough","$15.00"))
        categoryItemList3.add(CategoryItem(1,R.drawable.t6,"Set of Ploughs","$30.00"))


        val allcategory:MutableList<allcategory> = ArrayList()
        allcategory.add(allcategory("Fertilizers",categoryItemList))
        allcategory.add(allcategory("Seeds",categoryItemList2))
        allcategory.add(allcategory("Tools",categoryItemList3))
        setMainCategoryRecycler(allcategory)




        var helper=MyHelper(applicationContext)
        var db:SQLiteDatabase=helper.readableDatabase

        drawerLayout=findViewById(R.id.drawerLayout)
        val navView:NavigationView=findViewById(R.id.nav_view)

        toggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener{
            it.isChecked=true
            when(it.itemId){

                R.id.nav_home ->{
                    val intent: Intent =Intent(this,MainActivity::class.java)
                    startActivity(intent)

                }
                R.id.nav_logout->{
                    sendNotification("Logout Successfully", "User Successfully Logged Out !")
                }
//                R.id.nav_aboutus ->replacefragment(AboutusFragment(),"E-Agro")
                R.id.nav_notifications->{
                    val intent: Intent =Intent(this,Notifications::class.java)
                    startActivity(intent)

                }
//                R.id.nav_offerforu->replacefragment(OffersFragment(),"E-Agro")
//                R.id.nav_orders->replacefragment(MyordersFragment(),"E-Agro")
                 R.id.nav_login -> {
                    val intent: Intent =Intent(this,Login::class.java)
                    startActivity(intent)

                }
//                R.id.nav_editprofile->replacefragment(EditprofileFragment(),"E-Agro")
                R.id.nav_signup->{
                    val intent: Intent =Intent(this,Signup::class.java)
                    startActivity(intent)

                }

            }
            true
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        item.isChecked=true
//        when(item.itemId){
//            R.id.nav_search ->replacefragment(SearchFragment(),"E-Agro")
//            R.id.nav_account ->replacefragment(AccountFragment(),"E-Agro")
//        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_drawer, menu)
        return true
    }

    private fun createnotificationchannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
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

    private fun sendNotification(title:String,descnotification:String){
        val intent=Intent(this,MainActivity::class.java).apply{
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingintent:PendingIntent= PendingIntent.getActivity(this,0,intent,0)
        val bitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.logo)
        val bitmaplargeicon=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.logo)

        val builder=NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(descnotification)
                .setLargeIcon(bitmaplargeicon)
                .setStyle(NotificationCompat.BigTextStyle().bigText(descnotification))
                .setContentIntent(pendingintent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notificationid,builder.build())
        }

    }
//recycler view function

    private fun setMainCategoryRecycler(allcategory:List<allcategory>){
        mainCategoryRecycler=findViewById(R.id.main_recycler)
        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        mainCategoryRecycler!!.layoutManager=layoutManager
        mainRecyclerAdapter= mainrecycleradapter(this,allcategory)
        mainCategoryRecycler!!.adapter=mainRecyclerAdapter
    }

}