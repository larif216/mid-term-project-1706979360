package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.ScoreDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.UserDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.ScoreModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserWithScores

class UserRepository private constructor(private val userDao: UserDao, private val scoreDao: ScoreDao) {

    fun isValidAccount(username: String, password: String): Boolean {
        val user = userDao.getUser(username)

        return if (user != null) user.password == password
        else false
    }

    fun insertUser(username: String, password: String) {
        val newUser = UserModel(username = username, password = password)
        userDao.insert(newUser)
        val newUserId = userDao.getUser(username).id
        for (i in 1..30) {
            scoreDao.insert(
                ScoreModel(userId = newUserId, juzId = i, score = 0)
            )
        }
    }

    fun updateScore(id: Int, username: String, score: Int, juzId: Int) {
        val user = userDao.getUser(username)
        val newScore = ScoreModel(scoreId = id, userId = user.id, juzId = juzId, score = score)
        scoreDao.update(newScore)
        Log.d("Score updated", newScore.toString())
    }

    fun getUserWithScores(username: String): LiveData<UserWithScores>? {
        return userDao.getUserWithScores(username)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao, scoreDao: ScoreDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(userDao, scoreDao)
            }
            return instance!!
        }
    }
}