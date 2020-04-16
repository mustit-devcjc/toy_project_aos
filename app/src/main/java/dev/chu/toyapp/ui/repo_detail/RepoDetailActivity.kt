package dev.chu.toyapp.ui.repo_detail

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.GlideApp
import dev.chu.toyapp.etc.extensions.TAG

class RepoDetailActivity : BaseActivity() {

    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.activity_detail

    private lateinit var detailIv: ImageView
    private lateinit var detailTvName: TextView
    private lateinit var detailTvUpdateAt: TextView

    override fun initView(savedInstanceState: Bundle?) {
        Log.i(TAG, "initView")

        // region findViewById
        detailIv = findViewById(R.id.detail_iv)
        detailTvName = findViewById(R.id.detail_tv_name)
        detailTvUpdateAt = findViewById(R.id.detail_tv_updated_at)
        // endregion

        val bundle = intent.getBundleExtra(Const.EXTRA.USER_INFO)
        val data = bundle?.get(Const.ARGS.REPOS) as GithubRepos

        GlideApp.with(this)
            .load(data.owner.avatarUrl)
            .apply(
                RequestOptions().transform(CenterCrop()).diskCacheStrategy(
                    DiskCacheStrategy.RESOURCE
                )
            )
            .override(200, 200)
            .into(detailIv)

        detailTvName.text = data.name
        val str =
            "description : ${data.description}\nupdatedAt : ${data.updatedAt.toString()}\nlanguage : ${data.language}\nwatchers: ${data.watchers}" +
                    "\nopenIssues : ${data.openIssues}\nforks : ${data.forks}" +
                    "\nhtmlUrl : ${data.htmlUrl}\nlicense name : ${data.license?.name}\nlicense url : ${data.license?.url}"
        detailTvUpdateAt.text = str
    }
}