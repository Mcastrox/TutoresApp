package com.example.tutores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tutores.databinding.ActivityLoginBinding
import com.example.tutores.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // ARREGLAR ACTION BAR NAVIGATION
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginAction.setOnClickListener{
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() ){

                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Usuario creado de manera correcta",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                "No se ha podido crear el usuario",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Por favor rellena todos los campos", Toast.LENGTH_LONG).show()
            }
        }


    }
}