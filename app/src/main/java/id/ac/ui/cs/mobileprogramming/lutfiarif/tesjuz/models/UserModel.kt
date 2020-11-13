package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_table", indices = arrayOf(
    Index(
        value = arrayOf("username"),
        unique = true
    )
))
data class UserModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id:Int = 0,

    @ColumnInfo(name = "username")
    var username:String,

    @ColumnInfo(name = "password")
    var password:String
)