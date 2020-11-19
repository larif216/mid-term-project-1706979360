package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters.QuizRecyclerViewAdapter
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.QuestionModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.utils.ToastUtils
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.JuzViewModel
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import kotlin.random.Random

class QuizFragment: Fragment(), QuizRecyclerViewAdapter.OnOptionClickListener {
    private lateinit var viewModel: JuzViewModel
    private lateinit var juzData: JuzWithAyah
    private lateinit var question: QuestionModel
    private lateinit var adapter: QuizRecyclerViewAdapter
    private lateinit var tvQuestion: TextView
    private lateinit var rvOption: RecyclerView
    private var questionNumber = 0
    private var score = 100
    private var ayahCounter = 0
    private var currentAyah = -1

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

            if (questionNumber == 0) {
                questionNumber++
                ayahCounter++
                question = getQuestion()
                adapter = QuizRecyclerViewAdapter(question.option, this)
                tvQuestion = view.tvQuestion
                rvOption = view.option
                tvQuestion.text = question.question.text
                rvOption.adapter = QuizRecyclerViewAdapter(question.option, this)
            }
        }

    }

    private fun getQuestion(): QuestionModel {
        if (currentAyah == -1) {
            do {
                currentAyah = Random.nextInt(0, juzData.ayahs.size - 1)
            } while (juzData.ayahs.size - currentAyah <= 8)
        } else {
            currentAyah++
        }
        val randomAns = Random.nextInt(0, 3)
        val unavailableList = mutableListOf<Int>()
        val alphabetOption = listOf("A", "B", "C", "D")
        unavailableList.add(currentAyah)
        val listOption = mutableListOf<QuestionModel.Option>()
        for (i in 0..3) {
            var optionText: String
            if (randomAns == i) {
                optionText = juzData.ayahs[currentAyah + 1].text
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
        val question = juzData.ayahs[currentAyah]
        return QuestionModel(question, listOption, currentAyah)
    }

    override fun onOptionClick(option: QuestionModel.Option) {
        if (option.isAnswer) {
            ToastUtils(context!!, "Your answer is Correct", 1).make().show()
//            Toast.makeText(context, "Your answer is Correct", Toast.LENGTH_SHORT).show()
        } else {
            score--
            ToastUtils(context!!, "Your answer is Wrong", 0).make().show()
//            Toast.makeText(context, "Your answer is Wrong", Toast.LENGTH_SHORT).show()
        }

        if (ayahCounter == 8) {
            questionNumber++
            currentAyah = -1
            ayahCounter = 0
        } else {
            ayahCounter++
        }

        question = getQuestion()
        adapter.updateData(question.option)
        tvQuestion.text = question.question.text
        rvOption.adapter!!.notifyDataSetChanged()
    }
}