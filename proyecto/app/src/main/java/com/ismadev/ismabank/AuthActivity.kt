package com.ismadev.ismabank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1_000)
        setTheme(R.style.Theme_IsmaBank)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)




        // Setup

        setup()



    }

    private fun setup(){

        title = "Autenticación"

        var registerButton = findViewById<Button>(R.id.registerButton)
        var loginButton = findViewById<Button>(R.id.loginButton)
        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)

        registerButton.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),
                                            password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "")
                    }else{
                        showAlert()
                    }
                }
            }
        }

        loginButton.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),
                    password.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "")
                    }else{
                        showAlert()
                    }
                }
            }
        }

    }

    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog : AlertDialog = builder.create()
        dialog.show()

    }

    private fun showHome(email : String){

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
        }

        startActivity(homeIntent)

    }


}