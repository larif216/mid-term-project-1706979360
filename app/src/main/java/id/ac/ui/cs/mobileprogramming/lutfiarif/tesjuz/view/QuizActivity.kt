package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments.QuizFragment

class QuizActivity: AppCompatActivity() {

    private lateinit var quizFragment: QuizFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        if (savedInstanceState == null) {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt("juzNumber", intent.getIntExtra("juzNumber", -1))
            args.putInt("scoreId", intent.getIntExtra("scoreId", -1))
            fragment.arguments = args
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_quiz_container, fragment)
                .commit()
        }
    }
}