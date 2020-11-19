package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters.JuzRecyclerViewAdapter
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.ScoreModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserWithScores
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.QuizActivity
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.JuzViewModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.UserViewModel

class JuzListFragment: Fragment(), JuzRecyclerViewAdapter.OnJuzClickListener {
    private lateinit var juzViewModel: JuzViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var selectedScore: ScoreModel
    private lateinit var adapter: JuzRecyclerViewAdapter
    private lateinit var username: String
    private var juzData: JuzWithAyah? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_juz_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is RecyclerView) {

            view.layoutManager = LinearLayoutManager(context)

            juzViewModel = ViewModelProviders.of(this, context?.let { JuzViewModel.Factory(it) }).get(JuzViewModel::class.java)

            userViewModel = ViewModelProviders.of(this, context?.let { UserViewModel.Factory(it) }).get(UserViewModel::class.java)
            username = requireActivity().getSharedPreferences("Tes Juz", Context.MODE_PRIVATE).getString("username", "")!!
            userViewModel.getUserWithScores(username)!!.observe(viewLifecycleOwner, Observer {
                adapter = JuzRecyclerViewAdapter(it.scores as MutableList<ScoreModel>, this)
                view.adapter = adapter
            })

        }
    }

    override fun onJuzClick(juzData: ScoreModel) {
        selectedScore = juzData
        getJuzData().execute(juzData.juzId)
    }

    inner class getJuzData: AsyncTask<Int, Void, JuzWithAyah>() {
        private lateinit var progressDialog: ProgressDialog

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Getting Juz Data....")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }

        override fun doInBackground(vararg p0: Int?): JuzWithAyah {
            return juzViewModel.getJuzWithAyah(p0[0]!!)
        }

        override fun onPostExecute(result: JuzWithAyah?) {
            super.onPostExecute(result)
            juzData = result
            progressDialog.dismiss()

            if (result!!.ayahs.isEmpty()) {
                Toast.makeText(context, "Failed to get juz data", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(requireActivity(), QuizActivity::class.java)
                intent.putExtra("juzNumber", result.juz.number)
                intent.putExtra("scoreId", selectedScore.scoreId)
                startActivity(intent)
            }
        }
    }
}