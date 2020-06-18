package com.uniat.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_envia_mensaje.*

class EnviaMensajeActivity : AppCompatActivity() {

    var mensaje:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_envia_mensaje)

        mensaje = intent.getStringExtra("mensaje")

        buttonVer.setOnClickListener {
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show()
        }

        buttonEnviar.setOnClickListener {
            var intentMensaje = Intent(Intent.ACTION_SEND)
            intentMensaje.setType("text/plain")
            intentMensaje.putExtra(Intent.EXTRA_TEXT,mensaje)
            startActivity(intentMensaje)
        }
    }
}