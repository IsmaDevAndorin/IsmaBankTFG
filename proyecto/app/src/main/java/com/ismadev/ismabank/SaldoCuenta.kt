package com.ismadev.ismabank

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SaldoCuenta : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val details = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saldo_cuenta)

        //Setup
        setup()

        //Load Data
        loadData()



    }

    private fun setup(){
        title = "Saldo en cuenta"


    }

    private fun loadData(){
        var saldoTextView = findViewById<TextView>(R.id.saldoEnCuentaTextView)
        var listaDetalleSaldo = findViewById<ListView>(R.id.listaDetalleSaldo)


        var email = FirebaseAuth.getInstance().currentUser?.email.toString()

        db.collection("users").document(email).get().addOnSuccessListener {
            var saldo = (it.get("saldoEnCuenta") as String?)!!.toInt()

            if(saldo >= 0){
            saldoTextView.setTextColor(Color.GREEN)
            }else{
                saldoTextView.setTextColor(Color.RED)
            }
            saldoTextView.setText((it.get("saldoEnCuenta") as String?) + "€")
        }

        details.add("Operacion 1")
        details.add("Operacion 2")
        details.add("Operacion 3")
        details.add("Operacion 4")
        details.add("Operacion 5")

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, details)

        listaDetalleSaldo.setAdapter(adapter);
    }

}