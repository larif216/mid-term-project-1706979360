package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.JuzWithAyah
import kotlinx.android.synthetic.main.card_juz.view.*

class JuzRecyclerViewAdapter(
    private val juzList: List<JuzWithAyah>,
    private val listener: OnJuzClickListener
): RecyclerView.Adapter<JuzRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_juz, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val juz = juzList[position]
        holder.juzNumberTv.text = "Juz ${juz.juz.number}"
    }

    override fun getItemCount(): Int {
        return juzList.size
    }

    interface OnJuzClickListener {
        fun onJuzClick(juzData: JuzWithAyah)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val juzNumberTv = view.juzNumber
        init {
            view.setOnClickListener(View.OnClickListener {
                listener.onJuzClick(juzList[position])
            })
        }
    }
}