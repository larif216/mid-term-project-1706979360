package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "ayah_table"
)
data class AyahModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,

    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "number")
    var number: Int,

    @ColumnInfo(name = "surah")
    var surah: String,

    @ColumnInfo(name = "juzNumber")
    var juzNumber: Int
)