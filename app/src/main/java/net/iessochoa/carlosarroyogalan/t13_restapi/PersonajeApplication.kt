package net.iessochoa.carlosarroyogalan.t13_restapi

import android.app.Application
import net.iessochoa.carlosarroyogalan.t13_restapi.data.repository.Repository

class PersonajeApplication: Application(){
    companion object{
        lateinit var application: PersonajeApplication
    }
    override fun onCreate() {
        super.onCreate()
        application = this
        Repository.iniciarDataBase(this)
    }
}