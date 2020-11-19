package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import kotlinx.android.synthetic.main.activity_quiz.view.*
import kotlinx.android.synthetic.main.fragment_result_quiz.view.*

class QuizResultFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.score.text = arguments!!.getInt("score").toString()

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

}