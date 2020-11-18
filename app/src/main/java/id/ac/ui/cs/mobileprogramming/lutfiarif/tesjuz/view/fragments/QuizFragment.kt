package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters.QuizRecyclerViewAdapter
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.QuestionModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.JuzViewModel
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import kotlin.random.Random

class QuizFragment: Fragment(), QuizRecyclerViewAdapter.OnOptionClickListener {
    private lateinit var viewModel: JuzViewModel
    private lateinit var juzData: JuzWithAyah
    private lateinit var question: QuestionModel
    private var questionNumber = 0
    private var currentAyah = -1
    private var score = 100
    private var ayahCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, context?.let { JuzViewModel.Factory(it) }).get(JuzViewModel::class.java)
        juzData = viewModel.getJuzWithAyah(arguments!!.getInt("juzNumber"))

        if (view.option is RecyclerView) {
            view.option.layoutManager = LinearLayoutManager(context)
            question = getQuestion()
            view.tvQuestion.text = question.question.text
            view.option.adapter = QuizRecyclerViewAdapter(question.option, this)
        }

    }

    private fun getQuestion(): QuestionModel {
        var randomAyah: Int
        do {
            randomAyah = Random.nextInt(0, juzData.ayahs.size - 1)
        } while (juzData.ayahs.size - randomAyah <= 8)
        val randomAns = Random.nextInt(0, 3)
        val unavailableList = mutableListOf<Int>()
        val alphabetOption = listOf("A", "B", "C", "D")
        unavailableList.add(randomAyah)
        val listOption = mutableListOf<QuestionModel.Option>()
        for (i in 0..3) {
            var optionText: String
            if (randomAns == i) {
                optionText = juzData.ayahs[randomAyah + 1].text
            } else {
                var randomAyahForQuestion: Int = - 1
                do {
                    randomAyahForQuestion = Random.nextInt(0, juzData.ayahs.size - 1)
                } while (randomAyahForQuestion in unavailableList)
                unavailableList.add(randomAyahForQuestion)
                optionText = juzData.ayahs[randomAyahForQuestion].text
            }
            val isAnswer = randomAns == i
            listOption.add(QuestionModel.Option(alphabetOption[i], optionText, isAnswer))
        }
        val question = juzData.ayahs[randomAyah]
        return QuestionModel(question, listOption, randomAyah)
    }

    override fun onOptionClick(option: QuestionModel.Option) {
        Toast.makeText(context, "Option ${option.isAnswer} got clicked", Toast.LENGTH_SHORT).show()
    }
}