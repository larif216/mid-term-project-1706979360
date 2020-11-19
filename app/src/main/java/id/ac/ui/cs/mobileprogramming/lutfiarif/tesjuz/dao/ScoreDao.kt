package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.ScoreModel

@Dao
interface ScoreDao {
    @Insert
    fun insert(score: ScoreModel)

    @Update
    fun update(score: ScoreModel)
}