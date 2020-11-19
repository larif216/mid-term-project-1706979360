package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

import androidx.room.*

@Entity(tableName = "user_table", indices = arrayOf(
    Index(
        value = arrayOf("username"),
        unique = true
    )
))
data class UserModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,

    @ColumnInfo(name = "username")
    var username:String,

    @ColumnInfo(name = "password")
    var password:String
)

data class UserWithScores (
    @Embedded val user: UserModel,
    @Relation (
        parentColumn = "id",
        entityColumn = "userId"
    )
    val scores: List<ScoreModel>
)