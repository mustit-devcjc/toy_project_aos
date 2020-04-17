package dev.chu.toyapp.ui.user_repos

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseAdapter
import dev.chu.toyapp.base.BaseViewHolder
import dev.chu.toyapp.entity.GithubRepos

class UserReposAdapter(
    items: MutableList<GithubRepos>,
    private val callback: (GithubRepos) -> Unit
) : BaseAdapter<GithubRepos, UserReposViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_repo, parent, false)
        return UserReposViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

}