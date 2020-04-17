package dev.chu.toyapp.ui.repo_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.databinding.ActivityRepoDetailBinding
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.extensions.TAG

class RepoDetailActivity : BaseActivity<ActivityRepoDetailBinding>() {

    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.activity_repo_detail

    companion object {
        fun newIntent(context: Context, repos: GithubRepos) =
            Intent(context, RepoDetailActivity::class.java).apply {
                putExtra(Const.EXTRA.USER_INFO, bundleOf(Const.ARGS.REPOS to repos))
            }
    }

    override fun initView(savedInstanceState: Bundle?) {
        Log.i(TAG, "initView")

        val bundle = intent.getBundleExtra(Const.EXTRA.USER_INFO)
        val data = bundle?.get(Const.ARGS.REPOS) as GithubRepos

        binding.data = data
        binding.src = "description : ${data.description}\nupdatedAt : ${data.updatedAt.toString()}\nlanguage : ${data.language}\nwatchers: ${data.watchers}" +
                "\nopenIssues : ${data.openIssues}\nforks : ${data.forks}" +
                "\nhtmlUrl : ${data.htmlUrl}\nlicense name : ${data.license?.name}\nlicense url : ${data.license?.url}"
    }

}