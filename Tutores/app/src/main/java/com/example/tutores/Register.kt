package com.example.tutores

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutores.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Logica del registro del usuario

        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //En caso que el usuario ya posea una cuenta
        binding.alreadyHaveAccount.setOnClickListener {
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }

        binding.registerAction.setOnClickListener{
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmation = binding.confirmPass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmation.isNotEmpty()){
                if(pass == (confirmation)){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this,"Usuario creado de manera correcta",Toast.LENGTH_LONG).show()
                            val intent= Intent(this,Login::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,"No se ha podido crear el usuario",Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"La contraseña no coincide, por favor intenta de nuevo",Toast.LENGTH_LONG).show()
                }

            }
            else{
                Toast.makeText(this,"Por favor rellena todos los campos",Toast.LENGTH_LONG).show()
            }
        }
    }
}