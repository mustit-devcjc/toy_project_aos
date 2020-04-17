package dev.chu.toyapp.ui.user_repos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.etc.GlideApp
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

    private lateinit var adapter: UserReposAdapter
    private lateinit var userReposIv: ImageView
    private lateinit var userReposTv: TextView
    private lateinit var userReposRv: RecyclerView

    override fun initView(savedInstanceState: Bundle?) {
        Log.i(TAG, "initView")

        userReposIv = findViewById(R.id.user_repos_iv)
        userReposTv = findViewById(R.id.user_repos_tv)
        userReposRv = findViewById(R.id.user_repos_rv)

        userReposVM.userName = intent.getStringExtra(Const.EXTRA.USER_NAME) ?: "JC-Choo"

        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView() {
        adapter = UserReposAdapter(mutableListOf()) { repos ->
            startActivity(RepoDetailActivity.newIntent(this, repos))
        }
        userReposRv.adapter = adapter
    }

    private fun observeViewModel() {
        userReposVM.listUserRepos.observe(this, Observer {
            adapter.setNewItems(it)

            GlideApp
                .with(this)
                .load(if(!it.isNullOrEmpty())  it[0].owner.avatarUrl else getDrawable(R.drawable.ic_launcher_foreground))
                .apply(
                    RequestOptions()
                        .transform(CenterCrop())
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(userReposIv)
            userReposTv.text = "${it.size}개"
        })
    }
}