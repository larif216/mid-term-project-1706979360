package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.ScoreModel
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.UserWithScores
import kotlinx.android.synthetic.main.card_juz.view.*

class JuzRecyclerViewAdapter(
    private val juzList: MutableList<ScoreModel>,
    private val listener: OnJuzClickListener
): RecyclerView.Adapter<JuzRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_juz, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val juz = juzList[position]
        holder.juzNumberTv.text = "Juz ${juz.juzId}"
        var score = ""
        score += if (juz.score == 0) {
            "-"
        } else {
            juz.score.toString()
        }
        holder.juzScoreTv.text = score
    }

    override fun getItemCount(): Int {
        return juzList.size
    }

    fun updateData(newData: MutableList<ScoreModel>) {
        juzList.clear()
        juzList.addAll(newData)
        notifyDataSetChanged()
    }

    interface OnJuzClickListener {
        fun onJuzClick(juzData: ScoreModel)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val juzNumberTv = view.juzNumber
        val juzScoreTv = view.juzScore
        init {
            view.setOnClickListener(View.OnClickListener {
                listener.onJuzClick(juzList[position])
            })
        }
    }
}