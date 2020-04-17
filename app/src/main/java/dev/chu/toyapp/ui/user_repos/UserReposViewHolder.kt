package dev.chu.toyapp.ui.user_repos

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseViewHolder
import dev.chu.toyapp.entity.GithubRepos

class UserReposViewHolder(view: View, private val callback: (GithubRepos) -> Unit): BaseViewHolder(view) {
    
    private val tvTitle = view.findViewById<TextView>(R.id.user_repo_tv_title)
    private val tvLanguage = view.findViewById<TextView>(R.id.user_repo_tv_language)
    private val tvContent = view.findViewById<TextView>(R.id.user_repo_tv_content)
    private val tvTime = view.findViewById<TextView>(R.id.user_repo_tv_time)

    @SuppressLint("SetTextI18n")
    override fun bind(item: Any) {
        val data = item as GithubRepos

        tvTitle.text = "Name : ${item.name}"
        tvLanguage.text = "Language : ${item.language}"
        tvContent.text = "Description : ${item.description}"
        tvTime.text = item.updatedAt.toString().split("GMT")[0]

        itemView.setOnClickListener {
            callback(data)
        }
    }
}