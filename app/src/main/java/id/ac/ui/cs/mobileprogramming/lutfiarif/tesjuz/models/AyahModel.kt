package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "ayah_table"
)
@Parcelize
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
): Parcelable