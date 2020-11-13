package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserModel)

    @Query("select * from user_table where username like :username")
    fun getUser(username: String): UserModel
}