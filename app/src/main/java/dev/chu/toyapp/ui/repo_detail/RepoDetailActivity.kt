package dev.chu.toyapp.ui.repo_detail

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.base.DatabindingActivity
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.databinding.ActivityRepoDetailBinding
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.extensions.TAG
import dev.chu.toyapp.etc.extensions.applyMaterialTransform

//class RepoDetailActivity : BaseActivity<ActivityRepoDetailBinding>() {
//
//    @LayoutRes
//    override fun getLayoutRes(): Int = R.layout.activity_repo_detail
//
//    companion object {
//        fun newIntent(context: Context, repos: GithubRepos) =
//            Intent(context, RepoDetailActivity::class.java).apply {
//                putExtra(Const.EXTRA.USER_INFO, bundleOf(Const.ARGS.REPOS to repos))
//            }
//    }
//
//    override fun initView(savedInstanceState: Bundle?) {
//        Log.i(TAG, "initView")
//
//        val bundle = intent.getBundleExtra(Const.EXTRA.USER_INFO)
//        val data = bundle?.get(Const.ARGS.REPOS) as GithubRepos
//
//        binding.data = data
//        binding.src = "description : ${data.description}\nupdatedAt : ${data.updatedAt.toString()}\nlanguage : ${data.language}\nwatchers: ${data.watchers}" +
//                "\nopenIssues : ${data.openIssues}\nforks : ${data.forks}" +
//                "\nhtmlUrl : ${data.htmlUrl}\nlicense name : ${data.license?.name}\nlicense url : ${data.license?.url}"
//    }
//
//}

class RepoDetailActivity : DatabindingActivity() {

    companion object {
        fun startActivityModel(context: Context?, startView: View, repos: GithubRepos) {
            if (context is Activity) {
                val intent = Intent(context, RepoDetailActivity::class.java)
                intent.putExtra(Const.EXTRA.USER_INFO, bundleOf(Const.ARGS.REPOS to repos))
                val options = ActivityOptions.makeSceneTransitionAnimation(context, startView, repos.name)
                context.startActivity(intent, options.toBundle())
            }
        }
    }

    private val binding: ActivityRepoDetailBinding by binding(R.layout.activity_repo_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.getBundleExtra(Const.EXTRA.USER_INFO)
        val repos = bundle?.get(Const.ARGS.REPOS) as GithubRepos
        applyMaterialTransform(repos.name)
        binding.apply {
            data = repos
            src = "description : ${repos.description}\nupdatedAt : ${repos.updatedAt.toString()}\nlanguage : ${repos.language}\nwatchers: ${repos.watchers}" +
                    "\nopenIssues : ${repos.openIssues}\nforks : ${repos.forks}" +
                    "\nhtmlUrl : ${repos.htmlUrl}\nlicense name : ${repos.license?.name}\nlicense url : ${repos.license?.url}"
            activity = this@RepoDetailActivity
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}