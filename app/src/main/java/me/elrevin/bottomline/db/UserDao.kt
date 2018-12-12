package me.elrevin.bottomline.db

import android.arch.persistence.room.*
import android.arch.persistence.room.Dao
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User LIMIT 1")
    fun getUser(): Maybe<User>
}