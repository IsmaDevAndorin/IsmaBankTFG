package com.ismadev.ismabank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MisCitas : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_citas)

        val bundle = intent.extras
        val email = bundle?.getString("email")


        //Setup
        setup(email.toString())



    }

    private fun setup(email :String){
        title = "Mis Citas"
        var sucursal : String? = ""
        var dia : String? = ""
        var hora : String? = ""

        db.collection("users").document(email).collection(email).document("Citas").get().addOnSuccessListener {
            sucursal = it.get("Sucursal") as String?
            dia =  it.get("dia") as String?
            hora = it.get("hora") as String?
        }

        var textview = findViewById<TextView>(R.id.textView)
        var textview2 = findViewById<TextView>(R.id.textView2)
        var textview3 = findViewById<TextView>(R.id.textView3)

        textview.text = sucursal
        textview2.text = dia
        textview3.text = hora




    }
}