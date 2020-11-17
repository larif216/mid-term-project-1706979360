package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.databases.TesJuzDatabase
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.repositories.JuzRepository

class JuzViewModel(context: Context): ViewModel() {
    private val juzRepository = JuzRepository.getInstance(TesJuzDatabase.getTesJuzDatabase(context).juzDao(), TesJuzDatabase.getTesJuzDatabase(context).ayahDao(), context)

    internal fun getJuzWithAyah(juzNumber: Int): JuzWithAyah {
        return juzRepository.getJuzWithAyah(juzNumber)
    }

    internal fun getAllJuzWithAyah(): List<JuzWithAyah> {
        return juzRepository.getAllJuzWithAyah()
    }

    class Factory internal constructor(context: Context): ViewModelProvider.Factory {
        private val context: Context = context.applicationContext

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return JuzViewModel(context) as T
        }
    }
}