package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserWithScores

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserModel)

    @Query("select * from user_table where username like :username")
    fun getUser(username: String): UserModel

    @Transaction
    @Query("select * from user_table where username like :username")
    fun getUserWithScores(username: String): LiveData<UserWithScores>
}