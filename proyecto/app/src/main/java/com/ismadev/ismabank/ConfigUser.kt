package com.ismadev.ismabank

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ConfigUser : AppCompatActivity() {
    lateinit var usuario : User
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_user)
        val bundle = intent.extras
        var email = FirebaseAuth.getInstance().currentUser?.email.toString()
        var saveUserButton = findViewById<Button>(R.id.saveUserButton)

        var username = findViewById<EditText>(R.id.username)



        //Setup
        setup(email, username)
        var saldo : String = "0"
        db.collection("users").document(email).get().addOnSuccessListener {
           saldo  = (it.get("saldoEnCuenta") as String)

        }



        saveUserButton.setOnClickListener {
            saveUser(email.toString(),username.text.toString(), saldo)

        }



    }

    private fun setup(email : String, username : EditText){

        db.collection("users").document(email).get().addOnSuccessListener {
            username.setText(it.get("username") as String?)
        }

    }

    private fun saveUser(email: String, name: String, saldo : String){

        try {
            db.collection("users").document(email).set(
                hashMapOf("username" to name, "saldoEnCuenta" to saldo)
            )

            var toast = Toast.makeText(this,"Username registrado satisfactoriamente",Toast.LENGTH_LONG)
            toast.show()
        }catch (e : Exception){
            var toast = Toast.makeText(this,"Username no se ha podido registrar",Toast.LENGTH_LONG)
            toast.show()
        }


    }

    /*private fun saveUser(user : User){
        db.collection("users").document(user.email).set(
            hashMapOf("username" to user.name)
        )
    }*/

}