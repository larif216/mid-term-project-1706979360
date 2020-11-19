package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

import androidx.room.*

@Entity(
    tableName = "juz_table",
)
data class JuzModel(
    @PrimaryKey
    @ColumnInfo(name = "number")
    var number: Int,
)

data class JuzWithAyah (
    @Embedded
    var juz: JuzModel,

    @Relation(
        parentColumn = "number",
        entityColumn = "juzNumber"
    )
    val ayahs: List<AyahModel>
)
