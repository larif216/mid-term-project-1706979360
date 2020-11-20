package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        activity?.onBackPressedDispatcher?.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val builder = AlertDialog.Builder(activity)
                builder.setMessage("Are you sure you want quit? Your progress will be lost.")
                builder.setPositiveButton("Yes"
                ) { _, _ -> activity?.finish() }
                builder.setNegativeButton("No", null)
                builder.show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
            questionNumber = savedInstanceState.getInt("questionNumber")
            score = savedInstanceState.getInt("score")
            ayahCounter = savedInstanceState.getInt("ayahCounter")
            currentAyah = savedInstanceState.getInt("currentAyah")
            question = savedInstanceState.getParcelable("question")!!
        }
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, context?.let { JuzViewModel.Factory(it) }).get(JuzViewModel::class.java)
        juzData = viewModel.getJuzWithAyah(arguments!!.getInt("juzNumber"))

        if (view.option is RecyclerView) {
            view.option.layoutManager = LinearLayoutManager(context)

            if (savedInstanceState != null) {
                tvQuestion = view.tvQuestion
                rvOption = view.option
                adapter = QuizRecyclerViewAdapter(question.option, this)
                tvQuestion.text = question.question.text
                rvOption.adapter = adapter
            } else {
                if (questionNumber == 0) {
                    questionNumber++
                    ayahCounter++
                    question = getQuestion()
                    adapter = QuizRecyclerViewAdapter(question.option, this)
                    tvQuestion = view.tvQuestion
                    rvOption = view.option
                    tvQuestion.text = question.question.text
                    rvOption.adapter = adapter
                }
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
            ToastUtils(context!!, R.string.correct, 1).show()
        } else {
            score--
            ToastUtils(context!!, R.string.wrong, 0).show()
        }

        if (ayahCounter == 8) {
            if (questionNumber == 5) {
                val fragment = QuizResultFragment()
                val args = Bundle()
                args.putInt("juzNumber", juzData.juz.number)
                args.putInt("score", score)
                args.putInt("scoreId", arguments!!.getInt("scoreId"))
                fragment.arguments = args
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_quiz_container, fragment)
                    .commit()
            } else {
                questionNumber++
                currentAyah = -1
                ayahCounter = 0
            }
        } else {
            ayahCounter++
        }

        question = getQuestion()
        adapter.updateData(question.option)
        tvQuestion.text = question.question.text
        rvOption.adapter!!.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("questionNumber", questionNumber)
        outState.putInt("score", score)
        outState.putInt("ayahCounter", ayahCounter)
        outState.putInt("currentAyah", currentAyah)
        outState.putParcelable("question", question)
    }
}