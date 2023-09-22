package com.example.etrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.ktx.Firebase
import java.lang.ref.PhantomReference

class RegisterActivity : AppCompatActivity() {

    private lateinit var editText2: EditText
    private lateinit var editText3Password: EditText
    private lateinit var registerButton: Button

    private lateinit var database: FirebaseDatabase
    private lateinit var userReference: DatabaseReference
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        editText2 = findViewById(R.id.text2)
//        editText3Password = findViewById(R.id.text3)
//        buttonRegister = findViewById(R.id.regiterButton)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userReference = database.getReference("users")

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.text2).text.toString()
            val password = findViewById<EditText>(R.id.text3).text.toString()

            if(email.isNullOrBlank() || password.isNullOrBlank()) {
                Toast.makeText(this,"enter your credentials",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registration Succesful!", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this, HomepageActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Registration Failed.Please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

            val user = User(email,password)

            userReference.push().setValue(user).addOnSuccessListener {
                showToast("user data added successfully")
                clearInputFields()
            }
                .addOnFailureListener {
                    showToast("Failed to add user data : ${it.message}")
                }

        }



    }

    private fun clearInputFields() {
        editText2.text.clear()
        editText3Password.text.clear()
    }

    private fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}

data class User(val email: String,val password: String)
