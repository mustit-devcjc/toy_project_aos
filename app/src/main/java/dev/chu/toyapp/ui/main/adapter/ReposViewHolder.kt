package dev.chu.toyapp.ui.main.adapter

import android.view.View
import androidx.core.view.ViewCompat
import dev.chu.toyapp.base.BaseViewHolder2
import dev.chu.toyapp.databinding.ItemRepoBinding
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.extensions.bindings
import dev.chu.toyapp.ui.repo_detail.RepoDetailActivity

//class ReposViewHolder(view: View) : BaseViewHolder<ItemRepoBinding>(view)

class ReposViewHolder(view: View) : BaseViewHolder2(view) {

    private lateinit var data: GithubRepos
    private val binding: ItemRepoBinding by bindings(view)

    override fun bindData(data: Any) {
        if(data is GithubRepos) {
            this.data = data
            drawItemUI()
        }
    }

    private fun drawItemUI() {
        binding.apply {
            ViewCompat.setTransitionName(binding.repoContainer, data.name)
            item = data
            executePendingBindings()
        }
    }

    override fun onClick(v: View?) =
        RepoDetailActivity.startActivityModel(context(), binding.repoContainer, data)

    override fun onLongClick(v: View?): Boolean = false

}