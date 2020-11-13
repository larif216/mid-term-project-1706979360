package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.UserDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel

class UserRepository(private val userDao: UserDao) {
    private val user: LiveData<UserModel>? = null

    fun isValidAccount(username: String, password: String): Boolean {
        val user = userDao.getUser(username)
        return user.password == password
    }

    fun insert(username: String, password: String) {
        val newUser = UserModel(username = username, password = password)
        userDao.insert(newUser)
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