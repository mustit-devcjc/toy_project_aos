package dev.chu.toyapp.ui.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseViewHolder
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.GlideApp

class ReposViewHolder(view: View, private val callback: (GithubRepos) -> Unit) : BaseViewHolder(view) {

    private val ivAvatar = itemView.findViewById<ImageView>(R.id.repo_iv)
    private val tvTitle = itemView.findViewById<TextView>(R.id.repo_tv_title)
    private val tvContent = itemView.findViewById<TextView>(R.id.repo_tv_content)
    private val tvTime = itemView.findViewById<TextView>(R.id.repo_tv_time)

    override fun bind(item: Any) {
        val data = item as GithubRepos
        GlideApp.with(itemView.context)
            .load(data.owner.avatarUrl)
            .apply(RequestOptions().transform(CenterCrop(), CircleCrop()).diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .into(ivAvatar)

        tvTitle.text = data.name
        val text = "${data.fullName} (${data.language})"
        tvContent.text = text
        tvTime.text = data.updatedAt.toString().split("GMT")[0]

        itemView.setOnClickListener {
            callback(item)
        }
    }
}