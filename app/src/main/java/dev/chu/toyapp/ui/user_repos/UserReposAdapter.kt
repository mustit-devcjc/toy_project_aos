package dev.chu.toyapp.ui.user_repos

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.chu.basemodule.BaseAdapter
import dev.chu.basemodule.BaseViewHolder
import dev.chu.toyapp.R
import dev.chu.toyapp.entity.GithubRepos

class UserReposAdapter (
    items: MutableList<GithubRepos>
): BaseAdapter<GithubRepos, UserReposViewHolder>(items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_repo, parent, false)
        return UserReposViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as UserReposViewHolder).binding.item = items[position]
        (holder as UserReposViewHolder).binding.executePendingBindings()
    }

}