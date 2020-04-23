package dev.chu.toyapp.ui.main.adapter

import android.view.View
import androidx.core.view.ViewCompat
import dev.chu.toyapp.base.BaseViewHolder
import dev.chu.toyapp.databinding.ItemRepoBinding
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.ui.repo_detail.RepoDetailActivity

class ReposViewHolder(view: View) : BaseViewHolder<ItemRepoBinding>(view) {

    private lateinit var data: GithubRepos
    override fun bind(item: Any) {
        if(item is GithubRepos) {
            data = item
            drawItemUI()
        }
    }

    private fun drawItemUI() {
        binding.apply {
            ViewCompat.setTransitionName(binding.repoContainer, data.name)
            item = data
            executePendingBindings()
        }

        itemView.setOnClickListener {
            RepoDetailActivity.startActivityModel(itemView.context, binding.repoContainer, data)
        }
    }

}