package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters.JuzRecyclerViewAdapter
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.JuzViewModel

class JuzListFragment: Fragment(), JuzRecyclerViewAdapter.OnJuzClickListener {
    private lateinit var viewModel: JuzViewModel

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
        viewModel = ViewModelProviders.of(this, context?.let { JuzViewModel.Factory(it) }).get(JuzViewModel::class.java)
        val data = viewModel.getJuzWithAyah(juzData.juz.number)
        Log.d("JuzData", data.ayahs.size.toString())
    }
}