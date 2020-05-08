package dev.chu.toyapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.chu.basemodule.BaseAdapter
import dev.chu.basemodule.BaseViewHolder
import dev.chu.toyapp.R
import dev.chu.toyapp.entity.Users

class UsersAdapter(
    items: MutableList<Users>
) : BaseAdapter<Users, UsersViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UsersViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as UsersViewHolder).binding.item = items[position]
        (holder as UsersViewHolder).binding.executePendingBindings()
    }
}