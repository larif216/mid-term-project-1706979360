package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.databases.TesJuzDatabase
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserWithScores
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories.UserRepository

class UserViewModel(context: Context): ViewModel() {
    private val userRepository = UserRepository.getInstance(TesJuzDatabase.getTesJuzDatabase(context).userDao(), TesJuzDatabase.getTesJuzDatabase(context).scoreDao())

    internal var userWithScoresLiveData: LiveData<UserWithScores>? = null

    internal fun createUser(username: String, password: String) {
        userRepository.insertUser(username, password)
    }

    internal fun isValidCredential(username: String, password: String): Boolean {
        return userRepository.isValidAccount(username, password)
    }

    internal fun getUserWithScores(username: String): LiveData<UserWithScores>? {
        if (userWithScoresLiveData == null) {
            userWithScoresLiveData =  userRepository.getUserWithScores(username)
        }
        return userWithScoresLiveData
    }

    internal fun getUser(username: String): UserModel? {
        return userRepository.getUser(username)
    }

    internal fun updateScore(id: Int, username: String, score: Int, juzId: Int) {
        userRepository.updateScore(id, username, score, juzId)
    }

    class Factory internal constructor(context: Context): ViewModelProvider.Factory {
        private val context: Context = context.applicationContext

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(context) as T
        }
    }

}