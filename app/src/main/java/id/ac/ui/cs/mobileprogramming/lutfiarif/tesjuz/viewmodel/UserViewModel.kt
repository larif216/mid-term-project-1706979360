package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.databases.TesJuzDatabase
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories.UserRepository

class UserViewModel(context: Context): ViewModel() {
    private val userRepository: UserRepository =
        UserRepository.getInstance(TesJuzDatabase.getTesJuzDatabase(context).userDao())

    internal fun createUser(username: String, password: String) {
        userRepository.insert(username, password)
    }

    internal fun isValidCredential(username: String, password: String): Boolean {
        return userRepository.isValidAccount(username, password)
    }

    internal fun getUser(username: String): UserModel? {
        return userRepository.get(username)
    }

    class Factory internal constructor(context: Context): ViewModelProvider.Factory {
        private val context: Context = context.applicationContext

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(context) as T
        }
    }

}