package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories

import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao.JuzDao
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah

class JuzRepository private constructor(private val juzDao: JuzDao){
    fun getJuzWithAyah(juzNumber: Int): JuzWithAyah {
        return juzDao.getJuzWithAyahByNumber(juzNumber)
    }

    fun getAllJuzWithAyah(): List<JuzWithAyah> {
        return juzDao.getAllJuzWithAyah()
    }

    companion object {
        private var instance: JuzRepository? = null

        fun getInstance(juzDao: JuzDao): JuzRepository {
            if (instance == null) {
                instance = JuzRepository(juzDao)
            }
            return instance!!
        }
    }
}