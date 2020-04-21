package dev.chu.toyapp.ui.user_repos

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.databinding.ActivityUserReposBinding
import dev.chu.toyapp.etc.extensions.TAG

class UserReposActivity : BaseActivity<ActivityUserReposBinding>() {
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.activity_user_repos

    private val userReposVM by lazy { ViewModelProvider(this)[UserReposViewModel::class.java] }

    override fun initView(savedInstanceState: Bundle?) {
        Log.i(TAG, "initView")

        binding.apply {
            vm = userReposVM
        }

        userReposVM.userName = intent.getStringExtra(Const.EXTRA.USER_NAME) ?: "JC-Choo"
    }
}