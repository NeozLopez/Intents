package com.uniat.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_mensaje.*

class MensajeActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensaje)

        seekBarEdad.setOnSeekBarChangeListener(this)
        buttonEnviar.setOnClickListener {
            var intent = Intent(this,EnviaMensajeActivity::class.java)
            intent.putExtra("mensaje",construyeMensaje())
            startActivity(intent)
        }
    }

    private fun construyeMensaje():String{
        var mensaje = ""
        mensaje += if(radioDia.isChecked) "Buenos dias " else "Buenas noches "
        mensaje += editTextNombre.text.toString() + ", "
        mensaje += editTextMensaje.text
        return  mensaje
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        textViewMuestraEdad.text = "Edad: $progress"

        if(progress in 18..60){
            buttonEnviar.visibility = View.VISIBLE
        }
        else{
            buttonEnviar.visibility = View.INVISIBLE
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }


}