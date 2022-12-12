package com.drodriguez.direhospitality

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    lateinit var fragmentLogin : Fragment
    lateinit var fragmentHomePaciente : Fragment
    lateinit var fragmentNuevaCita : Fragment
    lateinit var fragmentRegistrarUsuario : Fragment
    lateinit var fragmentPasswordOlvidada : Fragment
    lateinit var fragmentAcercaDe : Fragment
    lateinit var fragmentHomeDoctor : Fragment

    // ArrayList de Usuarios y Citas
    var usuariosList = ArrayList<Usuario>()
    var citasList = ArrayList<Cita>()
    var usuarioActivo: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentLogin = Login()
        fragmentHomePaciente = HomePacienteHome()
        fragmentNuevaCita = NuevaCita()
        fragmentRegistrarUsuario = RegistrarUsuario()
        fragmentPasswordOlvidada = PasswordOlvidada()
        fragmentAcercaDe = AcercaDe()


        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutPrincipal, fragmentLogin).commit()

    }


    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.N)
    // Función para cambiar de fragment al home de iniciar sesión
    fun onIni(view: View) {

            var email = findViewById<EditText>(R.id.emailAcceso).text.toString()
            var password = findViewById<EditText>(R.id.passwordAcceso).text.toString()

            val a = Usuario("a","a","a","a","a","a","a",true,true)
            val b = Usuario("b","b","b","b","b","b","b",true,false)
            usuariosList.add(a)
            usuariosList.add(b)
            println(usuariosList.get(0).correo)
            println(email)


            var usuarioLista  = usuariosList.stream().filter { usuario -> usuario.correo.equals(email,true)}.collect(
                Collectors.toList())
            println(usuarioLista.toString())


            if (usuarioLista.size==0){
                Toast.makeText(this,"Datos Incorrectos", Toast.LENGTH_LONG).show()
            }else{

                var usuario = usuarioLista.get(0)
                if((usuario.correo.equals(email, true)
                            && usuario.password.equals(password.toString(), true)) && usuario.isAdmin
                ) {
                    Toast.makeText(this, "Bienvenido Administrador", Toast.LENGTH_LONG).show()


                    fragmentHomeDoctor = HomeDoctor()
                    (fragmentHomeDoctor as HomeDoctor).llenarLista(citasList)

                    if (citasList.size == 0) {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("Opcion no disponible")
                            .setMessage(
                                "No hay citas, \n" +
                                        "para ver la lista primero hay que solicitar una cita"
                            )
                            .setNegativeButton("Volver") { dialog, which -> dialog.dismiss() }
                        builder.show()
                    } else {
                        println("hemos llegado hasta la opcion de crear recicler")

                        var s = supportFragmentManager.beginTransaction()
                        s.addToBackStack(null)
                        s.replace(R.id.layoutPrincipal, fragmentHomeDoctor).commit()
                    }
                }else if(
                    (usuario.correo.equals(email.toString(), true)
                            && usuario.password.equals(password.toString(), true)) && !usuario.isAdmin
                ){
                    Toast.makeText(this,"Acceso autorizado", Toast.LENGTH_LONG).show()

                    usuarioActivo = usuario

                   var s = supportFragmentManager.beginTransaction()
                    s.addToBackStack(null)
                       s.replace(R.id.layoutPrincipal,fragmentHomePaciente  ).commit()

                }
            }
        }


    //Función para crear un nuevo usuario en la aplicación
    fun onClickRegistro(view: View) {
        //Obtener datos del usuario
        var nombreRegistro = findViewById<android.widget.EditText>(R.id.regPerName).text.toString()
        var apellidoRegistro = findViewById<android.widget.EditText>(R.id.regPerApellidos).text.toString()
        var dniRegistro = findViewById<android.widget.EditText>(R.id.regPerDNI).text.toString()
        var fechaNacRegistro = findViewById<android.widget.EditText>(R.id.regPerFecNac).text.toString()
        var correoRegistro = findViewById<android.widget.EditText>(R.id.regPerEmail).text.toString()
        var passwordRegistro = findViewById<android.widget.EditText>(R.id.regPerPassword).text.toString()
        var terminosRegistro = findViewById<android.widget.CheckBox>(R.id.regPerAdmin).isChecked
        var isAdminRegistro = findViewById<android.widget.CheckBox>(R.id.regPerAdmin).isChecked

        //Crear usuario
        val usuario = Usuario(UUID.randomUUID().toString(),nombreRegistro, apellidoRegistro, dniRegistro, fechaNacRegistro, correoRegistro, passwordRegistro, terminosRegistro, isAdminRegistro)

        usuariosList.add(usuario)
        println(usuariosList.get(0).correo)
        println(usuariosList.get(0).password)

        println("Usuario creado: ${usuario.id}, ${usuario.nombre}, ${usuario.apellido}, ${usuario.dni}, ${usuario.fechaNacimiento}, ${usuario.correo}, ${usuario.password}, ${usuario.terminosAceptados}, ${usuario.isAdmin}")
        var s = supportFragmentManager.beginTransaction()
                s.addToBackStack(null)
            s.replace(R.id.layoutPrincipal, fragmentLogin).commit()

    }

    // Función para cambiar de fragment al home de registrar user
    fun onClickNuevoUser(view: View) {
        var s = supportFragmentManager.beginTransaction()
                s.addToBackStack(null)
            s.replace(R.id.layoutPrincipal, fragmentRegistrarUsuario).commit()

        }


    // Función para regustrar una cita
    fun onClickSolicitarCita(view: View) {
       var s = supportFragmentManager.beginTransaction()
        s.addToBackStack(null)
            s.replace(R.id.layoutPrincipal, fragmentNuevaCita).commit()
    }

    fun onClickEnviarCita(view: View) {
        // Obtener datos de la cita nueva de radiogroup
        var motivoCitaRegistroOne = findViewById<android.widget.RadioButton>(R.id.radioMedGene).isChecked
        var motivoCitaRegistroTwo = findViewById<android.widget.RadioButton>(R.id.radioAnalisis).isChecked
        var fechaCitaRegistro = findViewById<android.widget.EditText>(R.id.fechaCita).text.toString()
        var horaCitaRegistro = findViewById<android.widget.EditText>(R.id.horaCita).text.toString()
        var minutoCitaRegistro = findViewById<android.widget.EditText>(R.id.minutoCita).text.toString()

        // Crear cita
        var cita = Cita(UUID.randomUUID().toString(), motivoCitaRegistroOne, motivoCitaRegistroTwo, fechaCitaRegistro, horaCitaRegistro, minutoCitaRegistro, usuarioActivo?.dni.toString())
        citasList.add(cita)
        println(citasList.toString())
        println(citasList.get(0).analisisClinicos)
        println(citasList.get(0).id)
        println(citasList.get(0).horaCita)

        println("Cita creada: ${cita.id}, ${cita.medicinaGeneral}, ${cita.analisisClinicos}, ${cita.fechaCita}, ${cita.horaCita}, ${cita.minutoCita}")
        var s = supportFragmentManager.beginTransaction()
                s.addToBackStack(null)
            s.replace(R.id.layoutPrincipal, fragmentHomePaciente).commit()

    }

    fun onClickCancelarOperacion(view: View) {
        var s = supportFragmentManager.beginTransaction()
                s.addToBackStack(null)
            s.replace(R.id.layoutPrincipal, fragmentHomePaciente).commit()
    }

    fun onClickContraseñaOlvidada(view: View) {
        var s = supportFragmentManager.beginTransaction()
        s.addToBackStack(null)
            s.replace(R.id.layoutPrincipal, fragmentPasswordOlvidada).commit()
    }

    fun onClickRecuperar(view: View) {
       var s = supportFragmentManager.beginTransaction()
        s.addToBackStack(null)
            s.replace(R.id.layoutPrincipal, fragmentPasswordOlvidada).commit()
        Toast.makeText(this, "Se ha enviado un correo a su cuenta de correo:  ${R.id.emailRecu.absoluteValue.toString()}", Toast.LENGTH_LONG).show()
    }

    // menú
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    // items del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
           R.id.acercaDeItem -> {
               var s = supportFragmentManager.beginTransaction()
                       s.addToBackStack(null)
                    s.replace(R.id.layoutPrincipal, fragmentAcercaDe).commit()
                true
            }
            R.id.cerrarAppItem -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
