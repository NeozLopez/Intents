package com.uniat.intents

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_intents.*


class IntentsActivity : AppCompatActivity(),View.OnClickListener {

    val IMAGE_FROM_CAMERA = 50
    val CALL_PHONE_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)
        botonMensaje.setOnClickListener(this)
        botonTelefono.setOnClickListener(this)
        botonCamara.setOnClickListener(this)
        botonPaginaWeb.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var mensaje = Toast.makeText(this, "", Toast.LENGTH_LONG)
        when (v?.id) {
            R.id.botonMensaje -> {
                var intent = Intent(this, MensajeActivity::class.java)
                startActivity(intent)
            }
            R.id.botonTelefono -> {
                if(!editTextNumTelefono.text.isEmpty()){

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        //versiones nuevas
                        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED)
                        {
                            var intentTelefono = Intent(Intent.ACTION_CALL, Uri.parse("tel:${editTextNumTelefono.text}"))
                            startActivity(intentTelefono)
                        }
                        else{
                            //No tiene permiso o por que es la primera vez que pide permiso
                            if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //no se ha pedido permiso aun
                                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),CALL_PHONE_CODE)
                            }
                            else{
                                //El usuario no le dio permiso, se muestra verntana para dar permisos
                            }
                        }
                    }
                    else{
                        //Versiones antiguas
                        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED)
                        {
                            var intentTelefono = Intent(Intent.ACTION_CALL, Uri.parse("tel:${editTextNumTelefono.text}"))
                            startActivity(intentTelefono)
                        }
                        else{
                            Toast.makeText(this,"No tienes permiso",Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else{
                    editTextNumTelefono.error = "Campo obligatorio"
                }
            }
            R.id.botonPaginaWeb -> {
                mensaje.setText("Pagina web")
                if (!editTextPaginaWeb.text.isEmpty()) {
                    var intentWeb =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://" + editTextPaginaWeb.text))
                    startActivity(intentWeb)
                } else {
                    editTextPaginaWeb.error = "Campo obligatorio"
                }
            }
            R.id.botonCamara -> {
                var intentCamera = Intent("android.media.action.IMAGE_CAPTURE")
                startActivityForResult(intentCamera, IMAGE_FROM_CAMERA)
            }
        }
        mensaje.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            IMAGE_FROM_CAMERA -> {
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Se capturo una imagen", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "No se capturo una imagen", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray  ) {

        when(requestCode){
            CALL_PHONE_CODE->{
                //Estamos en el caso del permiso para el telefono
                var permission = permissions[0]
                var result = grantResults[0]

                if(permission.equals(Manifest.permission.CALL_PHONE)){
                    if(result==PackageManager.PERMISSION_GRANTED){
                        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED)
                        {
                            var intentTelefono = Intent(Intent.ACTION_CALL, Uri.parse("tel:${editTextNumTelefono.text}"))
                            startActivity(intentTelefono)
                        }
                        else{
                            Toast.makeText(this,"No tienes permiso",Toast.LENGTH_LONG).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "Se nego el permiso", Toast.LENGTH_LONG).show()
                    }
                }

            }
            else->{
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

}