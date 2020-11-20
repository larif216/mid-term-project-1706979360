package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah

@Dao
interface JuzDao {
    @Insert
    fun insert(juz: JuzModel)

    @Query("SELECT * FROM juz_table")
    fun getAllJuzWithAyah(): List<JuzWithAyah>

    @Query("SELECT * FROM juz_table WHERE number LIKE :juzNumber")
    fun getJuzWithAyahByNumber(juzNumber: Int): JuzWithAyah

    @Query ("SELECT * FROM juz_table")
    fun getJuzData(): Cursor
}