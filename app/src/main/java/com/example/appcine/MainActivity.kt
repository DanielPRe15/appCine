package com.example.appcine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    private lateinit var edtCliente: EditText
    private lateinit var edtCAdultos:EditText
    private lateinit var edtCNinos:EditText
    private lateinit var spnPelicula: Spinner
    private lateinit var rbtnComedias: RadioButton
    private lateinit var rbtnDramaticas:RadioButton
    private lateinit var rbtnTerror:RadioButton
    private lateinit var rbtnInfantiles:RadioButton
    private lateinit var btnProcesar: Button

    //Variables globales
    var nomGenero : String = ""
    var posGenero : Int = -1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCliente=findViewById(R.id.edtCliente)
        edtCAdultos= findViewById(R.id.edtCAdultos)
        edtCNinos= findViewById(R.id.edtCNinos)
        spnPelicula= findViewById(R.id.spnPelicula)
        rbtnComedias= findViewById(R.id.rbtnComedia)
        rbtnDramaticas= findViewById(R.id.rbtnDramaticas)
        rbtnTerror= findViewById(R.id.rbtnTerror)
        rbtnInfantiles= findViewById(R.id.rbtnInfantiles)
        btnProcesar= findViewById(R.id.btnProcesar);
        rbtnComedias.setOnClickListener{cambiarPelicula()}
        rbtnDramaticas.setOnClickListener{cambiarPelicula()}
        rbtnTerror.setOnClickListener{cambiarPelicula()}
        rbtnInfantiles.setOnClickListener{cambiarPelicula()}
        btnProcesar.setOnClickListener{procesar()}
    }

    fun cambiarPelicula(){

        //Validar que radio se ha seleccionado
        if(rbtnComedias.isChecked) {

            // Crear arreglo
            var peliculas = arrayOf("Super cool", "¿Qué pasó ayer?")
            nomGenero = rbtnComedias.text.toString()
            posGenero = 0
            updateSpinner(peliculas)
        }
        else
            if(rbtnDramaticas.isChecked) {

                // Crear arreglo
                var peliculas = arrayOf("Lo imposible ", "12 años de esclavitud?", "Historias cruzadas")
                nomGenero = rbtnDramaticas.text.toString()
                posGenero = 1
                updateSpinner(peliculas)
            }
        else
            if(rbtnTerror.isChecked){
                //Crear arreglo
                var peliculas = arrayOf("Annabelle 3","Nosotros","Un lugar en silencio","Halloween")
                nomGenero = rbtnTerror.text.toString()
                posGenero = 2
                updateSpinner(peliculas)
            }
        else
           {
                var peliculas = arrayOf("Alvin y las ardillas", "Arthur y los Minimoys", "Bolt", "Cars","Encantada")
               nomGenero = rbtnInfantiles.text.toString()
               posGenero = 3
                updateSpinner(peliculas)
            }
    }

    //Metodo que cambia los valores del Spinner
    fun updateSpinner(data:Array<String>){
        // Crear objetos de la clase ArrayAdapter y enviar el arreglo "peliculas"
        var adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data )

        // Enviar objeto adaptador al Spinner
        spnPelicula.adapter = adaptador
    }

    fun procesar(){
        //Crear objeto de la clase intent / para navegar entre pantallas.
        var intent = Intent(this, ProcesarActivity :: class.java)

        //Enviar los valores del cliente
        intent.putExtra("cliente",edtCliente.text.toString())

        //Enviar los valores de genero
        intent.putExtra("nombreGenero",nomGenero)

        //Enviar los datos de la pelicula
        intent.putExtra("nombrePelicula",spnPelicula.selectedItem.toString())

        //Enviar los valores de los asientos
        intent.putExtra("asientosAdultos",edtCAdultos.text.toString().toInt())
        intent.putExtra("asientosNinos",edtCNinos.text.toString().toInt())
        intent.putExtra("posicionGenero",posGenero)
        intent.putExtra("posicionPelicula",spnPelicula.selectedItemPosition)


        //Crear claves(variables) dentro del objeto "intent"




        //Direccionar
        startActivity(intent)



    }


}