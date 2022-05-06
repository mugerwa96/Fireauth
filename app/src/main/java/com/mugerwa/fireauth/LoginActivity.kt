package com.mugerwa.fireauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    //    registerlink
    private lateinit var registerLink:TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var loginEmail:EditText
    private  lateinit var loginPassword:EditText
    private lateinit var loginButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
// Initialize Firebase Auth
        auth = Firebase.auth

//        binding
        loginEmail=findViewById(R.id.loginEmail)
        loginPassword=findViewById(R.id.loginPassword)
        loginButton=findViewById(R.id.registerBtn)
//        actual values

        loginButton.setOnClickListener {
            val email=loginEmail.text.toString().trim()
            val password=loginPassword.text.toString().trim()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }




//        link to login activity
        registerLink=findViewById(R.id.registerLink)
        registerLink.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }


    }
}