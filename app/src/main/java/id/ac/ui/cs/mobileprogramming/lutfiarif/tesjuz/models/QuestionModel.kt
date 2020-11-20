package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionModel(
    val question: AyahModel,
    val option: MutableList<Option>,
    val ayahPosition: Int
): Parcelable {
    @Parcelize
    data class Option(
        val alphabet: String,
        val option: String,
        val isAnswer: Boolean
    ): Parcelable {

    }

}