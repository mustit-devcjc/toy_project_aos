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
//    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.activity_main

    private val backPress by lazy { BackPressCloseHandler(this) }

    override fun initView(savedInstanceState: Bundle?) {
        Log.i(TAG, "initView")

        binding.activity = this@MainActivity
        replaceFragment(binding.mainFl.id, ReposFragment())
    }

    override fun onBackPressed() {
        backPress.onBackPressed()
    }
}