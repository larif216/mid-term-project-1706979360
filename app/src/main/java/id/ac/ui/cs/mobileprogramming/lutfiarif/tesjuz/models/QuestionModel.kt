package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models

data class QuestionModel(
    val question: AyahModel,
    val option: List<Option>,
    val ayahPosition: Int
) {
    data class Option(
        val alphabet: String,
        val option: String,
        val isAnswer: Boolean
    ) {

    }

}