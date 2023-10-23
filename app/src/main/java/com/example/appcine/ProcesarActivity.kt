package com.example.appcine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class ProcesarActivity : AppCompatActivity() {

    //Atributos
    private lateinit var tvClienteR: TextView
    private lateinit var tvGeneroR: TextView
    private lateinit var tvPeliculaR: TextView
    private lateinit var tvCAdultosR: TextView
    private lateinit var  tvCNinosR: TextView
    private lateinit var  tvMontoAdultos: TextView
    private lateinit var  tvMontoNinos: TextView
    private lateinit var  tvMontoConfiteria: TextView
    private lateinit var  tvTotalPagar: TextView
    private lateinit var  edtCFamiliar: EditText
    private lateinit var  edtCPersonal: EditText
    private lateinit var  chkFamiliar: CheckBox
    private lateinit var  chkPersonal: CheckBox
    private lateinit var  imgPelicula: ImageView
    private lateinit var  btnCalcular: Button
    private lateinit var  btnVolver: Button

    //variables globales
    var posGen : Int = -1
    var posPeli : Int = -1
    var cantidadA : Int = -1
    var cantidadN : Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.procesar_main)

        //referencias
        tvClienteR= findViewById(R.id.tvClienteR)
        tvGeneroR= findViewById(R.id.tvGeneroR)
        tvPeliculaR= findViewById(R.id.tvPeliculaR)
        tvCAdultosR=findViewById(R.id.tvCAdultosR)
        tvCNinosR= findViewById(R.id.tvCNinosR)
        tvMontoAdultos= findViewById(R.id.tvMontoAdultosR)
        tvMontoNinos= findViewById(R.id.tvMontoNinosR)
        tvMontoConfiteria= findViewById(R.id.tvMontoConfiteriaR)
        tvTotalPagar= findViewById(R.id.tvTotalPagarR)
        edtCFamiliar= findViewById(R.id.edtCFamiliarR)
        edtCPersonal= findViewById(R.id.edtCPersonalR)
        chkFamiliar= findViewById(R.id.chkFamiliarR)
        chkPersonal= findViewById(R.id.chkPersonalR)
        imgPelicula= findViewById(R.id.imgPeliculaR)
        btnCalcular= findViewById(R.id.btnCalcularR)
        btnVolver= findViewById(R.id.btnVolverR)
        btnCalcular.setOnClickListener{calcular()}
        btnVolver.setOnClickListener{volver()}
        chkFamiliar.setOnClickListener{confiteria()}
        chkPersonal.setOnClickListener{confiteria()}

        verDatos()

    }



    fun confiteria(){

        //Evaluar estado de los checkbox
        if(chkFamiliar.isChecked){
            edtCFamiliar.isEnabled = true
            edtCFamiliar.requestFocus()
        }
        else {
            edtCFamiliar.isEnabled = false
            edtCFamiliar.setText("0")
        }

        if(chkPersonal.isChecked){
            edtCPersonal.isEnabled = true
            edtCPersonal.requestFocus()
        }
        else {
            edtCPersonal.isEnabled = false
            edtCPersonal.setText("0")
        }
    }

    //metodo para recuperar las claves
    fun verDatos(){

        //Recuperar Claves
        var data = intent.extras

        cantidadA = data!!.getInt("asientosAdultos")
        cantidadN = data!!.getInt("asientosNinos")

        //Mostrar la data
        tvClienteR.text = "Cliente: " + data!!.getString("cliente")
        tvGeneroR.text = "Genero: " + data!!.getString("nombreGenero")
        tvPeliculaR.text = "Pelicula: " + data!!.getString("nombrePelicula")
        tvCAdultosR.setText("Cantidad Asientos Adultos: " + cantidadA)
        tvCNinosR.setText("Cantidad Asientos Niños: " + cantidadN)



        posGen = data!!.getInt("posicionGenero")
        posPeli = data!!.getInt("posicionPelicula")

        //Crear identificador de la imagen
        var ID=0
        ID = this.resources.getIdentifier("p"+posGen+posPeli,"drawable",this.packageName)

        //Enviar el identificador "ID" a la imagen
        imgPelicula.setImageResource(ID)

    }

    fun calcular(){
        //Declarar variables
        var costoPelicula : Double
        var canFamiliar : Int
        var canPersonal : Int
        var montoAdultos : Double
        var montoNinos : Double
        var montoConfiteria : Double
        var montoTotal : Double


        if(posGen == 0 && posPeli== 0){

            costoPelicula = 35.5
        }
        else
            if(posGen == 0 && posPeli== 1){

                costoPelicula = 32.5
            }
        else
            if(posGen == 1 && posPeli== 0){

                costoPelicula = 30.5
            }
        else
            if(posGen == 1 && posPeli== 1){

            costoPelicula = 28.3
        }
        else
            if(posGen == 1 && posPeli== 2){

                costoPelicula = 25.5
            }
        else
            if(posGen == 2 && posPeli== 0){

            costoPelicula = 45.5
        }
        else
            if(posGen == 2 && posPeli== 1){

            costoPelicula = 40.3
        }
        else
            if(posGen == 2 && posPeli== 2){

            costoPelicula = 43.0
        }
        else
            if(posGen == 2 && posPeli== 3){

            costoPelicula = 38.9
        }
        else
            if(posGen == 3 && posPeli== 0){

            costoPelicula = 58.9
        }
        else
            if(posGen == 3 && posPeli== 1){

            costoPelicula = 57.0
        }
        else
            if(posGen == 3 && posPeli== 2){

            costoPelicula = 60.0
        }
        else
            if(posGen == 3 && posPeli== 3){

            costoPelicula = 65.5
        }
        else
        {
            costoPelicula = 57.8
        }

                canFamiliar = edtCFamiliar.text.toString().toInt()
                canPersonal = edtCPersonal.text.toString().toInt()

                montoAdultos = cantidadA * costoPelicula
                montoNinos = cantidadN * (costoPelicula - 10)
                montoConfiteria = canFamiliar * 35.4 + canPersonal * 12.9

                montoTotal = montoAdultos + montoNinos + montoConfiteria


                tvMontoAdultos.setText("Monto Adultos: " + montoAdultos)
                tvMontoNinos.setText("Monto Niños: " + montoNinos)
                tvMontoConfiteria.setText("Monto Confiteria: " + montoConfiteria)
                tvTotalPagar.setText("Total : " + montoTotal)

    }

    fun volver(){

        var intent = Intent(this,MainActivity :: class.java)
        startActivity(intent)
    }


}