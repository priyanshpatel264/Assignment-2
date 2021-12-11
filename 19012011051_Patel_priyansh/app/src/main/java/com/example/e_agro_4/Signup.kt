package com.example.e_agro_4

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var submitbtn: Button
    private lateinit var semail:EditText
    private lateinit var spassword:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()

        submitbtn=findViewById(R.id.ssubmitbtn)
        semail=findViewById(R.id.semail)
        spassword=findViewById(R.id.spassword)

        val text_login:TextView =findViewById(R.id.signuptextlogin)
        text_login.setOnClickListener{
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
        submitbtn.setOnClickListener {
            if(semail.text.toString().isEmpty()){
                semail.error="please enter email"
                semail.requestFocus()
            }
            if(spassword.text.toString().isEmpty()){
                spassword.error="please enter password"
                spassword.requestFocus()
            }
            auth.createUserWithEmailAndPassword(semail.text.toString(), spassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        val intent:Intent=Intent(this,Login::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Enter valid field values.",
                            Toast.LENGTH_SHORT).show()
                        val intent:Intent=Intent(this,Signup::class.java)
                        startActivity(intent)
                    }
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

    }

    private fun reload() {

    }
}