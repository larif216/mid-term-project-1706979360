package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.dao

import androidx.room.Dao
import androidx.room.Insert
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.AyahModel

@Dao
interface AyahDao {
    @Insert
    fun insert(ayah: AyahModel)

}