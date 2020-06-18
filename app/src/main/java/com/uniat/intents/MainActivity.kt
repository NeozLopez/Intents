package com.uniat.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val usuario = "diego"
    var pass = "123456789"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonLogin.setOnClickListener {

            if(validaFormulario()){

                if(validaUsuario()){
                    var intent = Intent(this,IntentsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"El usuario y/o contraseña no son validos",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validaFormulario():Boolean{
        var estado = !editTextUsuario.text.isEmpty() && !editTextPass.text.isEmpty()
        if(editTextUsuario.text.isEmpty()){
            editTextUsuario.error = "Campo obligatorio"
        }
        if(editTextPass.text.isEmpty()){
            editTextPass.error = "Campo obligatorio"
        }
        return estado
    }

    private fun validaUsuario():Boolean{
        val estado = editTextUsuario.text.toString() == usuario && editTextPass.text.toString() == pass
        return estado
    }
}