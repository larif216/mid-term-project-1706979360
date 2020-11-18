package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.app.ProgressDialog
import android.os.AsyncTask
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
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters.JuzRecyclerViewAdapter
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.JuzViewModel

class JuzListFragment: Fragment(), JuzRecyclerViewAdapter.OnJuzClickListener {
    private lateinit var viewModel: JuzViewModel
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

            viewModel = ViewModelProviders.of(this, context?.let { JuzViewModel.Factory(it) }).get(JuzViewModel::class.java)
            view.adapter = JuzRecyclerViewAdapter(viewModel.getAllJuzWithAyah(), this)
        }
    }

    override fun onJuzClick(juzData: JuzWithAyah) {
        getJuzData().execute(juzData.juz.number)
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
            return viewModel.getJuzWithAyah(p0[0]!!)
        }

        override fun onPostExecute(result: JuzWithAyah?) {
            super.onPostExecute(result)
            juzData = result
            progressDialog.dismiss()

            if (result!!.ayahs.isEmpty()) {
                Toast.makeText(context, "Failed to get juz data", Toast.LENGTH_SHORT).show()
            } else {
                val quizFragment = QuizFragment()
                val args = Bundle()
                args.putInt("juzNumber", result.juz.number)
                quizFragment.arguments = args
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, quizFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}