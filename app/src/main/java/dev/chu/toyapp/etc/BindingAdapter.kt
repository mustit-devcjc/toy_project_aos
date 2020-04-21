package dev.chu.toyapp.etc

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.data.LoadingState
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.entity.Users
import dev.chu.toyapp.etc.extensions.simpleToolbarWithHome
import dev.chu.toyapp.etc.extensions.startActivity
import dev.chu.toyapp.ui.main.adapter.ReposAdapter
import dev.chu.toyapp.ui.main.adapter.UsersAdapter
import dev.chu.toyapp.ui.repo_detail.RepoDetailActivity
import dev.chu.toyapp.ui.user_repos.UserReposActivity
import dev.chu.toyapp.ui.user_repos.UserReposAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:srcGlide")
    fun ImageView.setGlide(url: String) {
        GlideApp.with(this.context)
            .load(url)
            .apply(
                RequestOptions()
                    .transform(CenterCrop())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            )
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("app:srcGlideCircle")
    fun ImageView.setGlideCircle(url: String) {
        GlideApp.with(this.context)
            .load(url)
            .apply(
                RequestOptions()
                    .transform(CenterCrop(), CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            )
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("android:onClickEvent")
    fun View.setOnClickEvent(item: Any) {
        setOnClickListener {
            when(item) {
                is GithubRepos -> {
//                    context.startActivity(RepoDetailActivity.newIntent(context, item))
                    RepoDetailActivity.startActivityModel(context, this, item)
                }
                is Users -> {
                    val bundle = bundleOf(Const.EXTRA.USER_NAME to item.login)
                    context.startActivity<UserReposActivity>(bundle)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:data", "android:filterText"])
    fun RecyclerView.setRecyclerViewAdapterToReposFragment(
        data: MutableList<GithubRepos>?,
        filterText: String?
    ) {
        (adapter as? ReposAdapter)?.apply {
            if (data != null)
                setNewItems(data)

            if (!filterText.isNullOrBlank())
                filter(filterText)
        } ?: run {
            adapter = ReposAdapter(mutableListOf())
        }
    }

    @JvmStatic
    @BindingAdapter("android:data")
    fun RecyclerView.setRecyclerViewAdapterToUsersFragment(
        data: MutableList<Users>?
    ) {
        (adapter as? UsersAdapter)?.apply {
            if (data != null)
                setNewItems(data)
        } ?: run {
            adapter = UsersAdapter(mutableListOf())
        }
    }

    @JvmStatic
    @BindingAdapter("android:data")
    fun RecyclerView.setRecyclerViewAdapterToUserReposActivity(
        data: MutableList<GithubRepos>?
    ) {
        (adapter as? UserReposAdapter)?.apply {
            if (data != null)
                setNewItems(data)
        } ?: run {
            adapter = UserReposAdapter(mutableListOf())
        }
    }

    @JvmStatic
    @BindingAdapter("android:setVisibility")
    fun ProgressBar.setVisibility(state: LoadingState?) {
        state?.let {
            isVisible = when (it.status) {
                LoadingState.Status.SUCCESS -> false
                LoadingState.Status.LOADING -> true
                LoadingState.Status.FAILED -> false
            }
        }
    }





    @JvmStatic
    @BindingAdapter(value = ["simpleToolbarWithHome", "simpleToolbarTitle"])
    fun Toolbar.bindToolbarWithTitle(activity: AppCompatActivity, title: String) {
        activity.simpleToolbarWithHome(this, title)
    }
}