package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.models.QuestionModel
import kotlinx.android.synthetic.main.option.view.*

class QuizRecyclerViewAdapter(
    private val optionList: List<QuestionModel.Option>,
    private val listener: OnOptionClickListener
): RecyclerView.Adapter<QuizRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.option, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = optionList[position]
        holder.tvOptionAlphabet.text = option.alphabet
        holder.tvOption.text = option.option
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    interface OnOptionClickListener {
        fun onOptionClick(option: QuestionModel.Option)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvOption = view.tvOption
        val tvOptionAlphabet = view.tvOptionAlphabet
        init {
            view.setOnClickListener(View.OnClickListener {
                listener.onOptionClick(optionList[position])
            })
        }
    }
}