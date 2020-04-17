package dev.chu.toyapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseFragment
import dev.chu.toyapp.databinding.FragmentReposBinding
import dev.chu.toyapp.etc.extensions.TAG

class ReposFragment : BaseFragment<FragmentReposBinding>() {
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_repos

    private val viewModel by lazy { ViewModelProvider(this).get(ReposViewModel::class.java) }

    companion object {
        fun newInstance() = ReposFragment()
    }

    override fun setView(view: View, savedInstanceState: Bundle?, arguments: Bundle?) {
        Log.i(TAG, "setView")

        binding.reposVM = viewModel
    }
}