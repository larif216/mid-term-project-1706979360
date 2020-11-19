package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

import androidx.room.*

@Entity(tableName = "score_table")
data class ScoreModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "scoreId")
    var scoreId: Int = 0,

    @ColumnInfo(name = "userId")
    var userId: Int,

    @ColumnInfo(name = "juzId")
    var juzId: Int,

    @ColumnInfo(name = "score")
    var score: Int
)

data class JuzAndScore(
    @Embedded val juz: JuzModel,
    @Relation (
        parentColumn = "number",
        entityColumn = "scoreId"
    )
    val score: ScoreModel
)