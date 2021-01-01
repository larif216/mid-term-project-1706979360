package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_result_quiz.view.*

class QuizResultFragment: Fragment() {

    private lateinit var userViewModel: UserViewModel

    init {
        System.loadLibrary("native-lib")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scoreId = arguments!!.getInt("scoreId")
        val username = requireActivity().getSharedPreferences("Tes Juz", Context.MODE_PRIVATE).getString("username", "")
        val score = getFinalScore(arguments!!.getInt("totalWrongAnswer"))
        val juzNumber = arguments!!.getInt("juzNumber")

        userViewModel = ViewModelProviders.of(this, context?.let { UserViewModel.Factory(it) }).get(UserViewModel::class.java)
        userViewModel.updateScore(scoreId, username!!, score, juzNumber)

        view.score.text = score.toString()

        view.btn_reattempt.setOnClickListener {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt("juzNumber", arguments!!.getInt("juzNumber"))
            fragment.arguments = args
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_quiz_container, fragment)
                .commit()
        }

        view.btn_back_to_home.setOnClickListener {
            requireActivity().finish()
        }
    }

    private external fun getFinalScore(wrongAnswer: Int): Int

}