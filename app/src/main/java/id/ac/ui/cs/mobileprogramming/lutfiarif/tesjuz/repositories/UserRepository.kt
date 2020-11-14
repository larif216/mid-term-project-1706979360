package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.UserDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel

class UserRepository private constructor(private val userDao: UserDao) {
    private val user: LiveData<UserModel>? = null

    fun isValidAccount(username: String, password: String): Boolean {
        val user = userDao.getUser(username)

        return if (user != null) user.password == password
        else false
    }

    fun insert(username: String, password: String) {
        val newUser = UserModel(username = username, password = password)
        userDao.insert(newUser)
    }

    fun get(username: String): UserModel? {
        return userDao.getUser(username)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(userDao)
            }
            return instance!!
        }
    }
}