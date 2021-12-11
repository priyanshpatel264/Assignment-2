package com.example.e_agro_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var lemailid:EditText
    private lateinit var lpassword:EditText
    private lateinit var lloginbtn: Button
    private lateinit var headerusername:TextView
    private lateinit var headernewname:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        lemailid=findViewById(R.id.lemailid)
        lpassword=findViewById(R.id.lpassword)
        lloginbtn=findViewById(R.id.lloginbtn)
        headerusername=findViewById(R.id.headername)
        headernewname=findViewById(R.id.susername)
        val text_signup: TextView =findViewById(R.id.signuptextlogin)
        text_signup.setOnClickListener{
            val intent= Intent(this,Signup::class.java)
            startActivity(intent)
        }

        lloginbtn.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        if(lemailid.text.toString().isEmpty()){
            lemailid.error="please enter email"
            lemailid.requestFocus()
        }
        if(lpassword.text.toString().isEmpty()){
            lpassword.error="please enter password"
            lpassword.requestFocus()
        }
        auth.signInWithEmailAndPassword(lemailid.text.toString(), lpassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    updateUI(null)
                }
            }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }
    private fun updateUI(currentuser: FirebaseUser?) {
        if(currentuser!=null){
            val intent:Intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            headerusername.setText("Hello "+headernewname)
        }
        else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
    private fun reload() {

    }
}