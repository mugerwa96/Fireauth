package com.mugerwa.fireauth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
//    declaring variables
      private var mFirebaseAnalytics: FirebaseAnalytics? = null
//    loginlink
    private lateinit var loginLink:TextView


    private  lateinit var registerButton:Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // Initialize Firebase Auth
        auth = Firebase.auth
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


//        binding
        loginLink=findViewById(R.id.registerLink)
        registerButton=findViewById(R.id.registerBtn)




//        register button click
        registerButton.setOnClickListener {
            // actual values,we use trim to remove all the white spaces from the input fields
            val email=findViewById<EditText>(R.id.loginEmail).text.toString().trim()
            val password=findViewById<EditText>(R.id.loginPassword).text.toString().trim()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        // Sign in success, update UI with the signed-in user's information
//                            when successfully registered go to the  Home apge
                       val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()

                    }
                }
        }

//        register button click




//        linking to login
        loginLink.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


    }


//

}