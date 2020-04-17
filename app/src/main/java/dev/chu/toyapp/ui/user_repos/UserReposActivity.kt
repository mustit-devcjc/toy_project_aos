package dev.chu.toyapp.ui.user_repos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.etc.extensions.TAG
import dev.chu.toyapp.ui.main.adapter.ReposAdapter
import dev.chu.toyapp.ui.repo_detail.RepoDetailActivity

class UserReposActivity : BaseActivity() {
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.activity_user_repos

    companion object {
        fun newIntent(context: Context, login: String) =
            Intent(context, UserReposActivity::class.java).apply {
                putExtra(Const.EXTRA.USER_NAME, login)
            }
    }

    private val userReposVM by lazy { ViewModelProvider(this)[UserReposViewModel::class.java] }

    private lateinit var adapter: ReposAdapter
    private lateinit var rv: RecyclerView

    override fun initView(savedInstanceState: Bundle?) {
        Log.i(TAG, "initView")

        rv = findViewById(R.id.user_repos_rv)

        val name = intent.getStringExtra(Const.EXTRA.USER_NAME) ?: "JC-Choo"
        userReposVM.getUserRepos(name)

        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView() {
        adapter = ReposAdapter(mutableListOf()) { repos ->
            startActivity(RepoDetailActivity.newIntent(this, repos))
        }
        rv.adapter = adapter
    }

    private fun observeViewModel() {
        userReposVM.listUserRepos.observe(this, Observer {
            adapter.setNewItems(it)
        })
    }
}