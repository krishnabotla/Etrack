package com.example.etrack


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("isLoggedIn",false)) {
            val intent = Intent(this,HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }

        val regiterButton = findViewById<Button>(R.id.signin)
        regiterButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.text2).text.toString()
            val password = findViewById<EditText>(R.id.text3).text.toString()

            if(email.isNullOrBlank() || password.isNullOrBlank()) {
                Toast.makeText(this,"enter your credentials", Toast.LENGTH_SHORT).show()
            }
            else {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login Succesful!", Toast.LENGTH_SHORT).show()

                            val editor = sharedPreferences.edit()
                            editor.putBoolean("isLoggedIn", true)
                            editor.apply()

                            val intent = Intent(this, HomepageActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Login Failed.Please check your credentials",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }
        }
        val LoginLink = findViewById<TextView>(R.id.loginlink)
        LoginLink.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
