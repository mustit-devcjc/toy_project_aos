package dev.chu.toyapp.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder2(
    private val view: View
) : RecyclerView.ViewHolder(view),
    View.OnClickListener,
    View.OnLongClickListener {

    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    abstract fun bindData(data: Any)

    fun view(): View = view
    fun context(): Context = view.context
}