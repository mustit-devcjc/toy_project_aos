package dev.chu.toyapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseAdapter
import dev.chu.toyapp.base.BaseViewHolder
import dev.chu.toyapp.entity.Users

class UsersAdapter(
    items: MutableList<Users>,
    private val callback: (Users) -> Unit
) : BaseAdapter<Users, UsersViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return UsersViewHolder(inflater, callback)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }
}