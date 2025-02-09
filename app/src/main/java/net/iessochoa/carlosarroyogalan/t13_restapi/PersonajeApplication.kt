package net.iessochoa.carlosarroyogalan.t13_restapi

import android.app.Application
import net.iessochoa.carlosarroyogalan.t13_restapi.data.repository.Repository

class PersonajeApplication: Application(){
    //Objeto companion que permite acceder a la instancia global de la aplicación
    companion object{
        //Variable estática que mantiene la referencia a la instancia de la aplicación
        lateinit var application: PersonajeApplication
    }
    override fun onCreate() {
        //Llama al metodo onCreate de la clase base
        super.onCreate()
        //Asigna la instancia actual de la aplicacion a la variable estatica
        application = this
        //Inicia la base de datos utilizando el contexto de la aplicación
        Repository.iniciarDataBase(this)
    }
}