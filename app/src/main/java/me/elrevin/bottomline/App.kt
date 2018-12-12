package me.elrevin.bottomline

import android.app.Application
import android.arch.persistence.room.Room
import me.elrevin.bottomline.db.BlDataBase
import me.elrevin.bottomline.db.UserDao
import uy.kohesive.injekt.*
import uy.kohesive.injekt.api.*

class App: Application {
    constructor (): super()

    init {
        cntx = this
    }

    companion object : InjektMain() {
        @JvmStatic lateinit var cntx: App
        override fun InjektRegistrar.registerInjectables() {
            addSingletonFactory {
                Room.databaseBuilder(cntx, BlDataBase::class.java, "db").build()
            }
        }
    }
}