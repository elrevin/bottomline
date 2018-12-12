package me.elrevin.bottomline.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class BlDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}