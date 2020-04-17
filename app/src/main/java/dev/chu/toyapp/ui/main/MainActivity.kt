package dev.chu.toyapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseActivity
import dev.chu.toyapp.databinding.ActivityMainBinding
import dev.chu.toyapp.etc.extensions.TAG
import dev.chu.toyapp.etc.extensions.replaceFragment
import dev.chu.toyapp.etc.handler.BackPressCloseHandler

class MainActivity : BaseActivity<ActivityMainBinding>() {
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.activity_main

    private val backPress by lazy { BackPressCloseHandler(this) }

    override fun initView(savedInstanceState: Bundle?) {
        Log.i(TAG, "initView")

        replaceFragment(binding.mainFl.id, ReposFragment.newInstance())

        setBottomNavigationView()
    }

    private fun setBottomNavigationView() {
        binding.mainBnv.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_repos -> {
                    replaceFragment(R.id.main_fl, ReposFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_users -> {
                    replaceFragment(R.id.main_fl, UsersFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun onBackPressed() {
        backPress.onBackPressed()
    }
}
