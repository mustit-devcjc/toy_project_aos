package dev.chu.toyapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseFragment
import dev.chu.toyapp.base.DatabindingFragment
import dev.chu.toyapp.databinding.FragmentReposBinding
import dev.chu.toyapp.etc.extensions.TAG

//class ReposFragment : BaseFragment<FragmentReposBinding>() {
//    @LayoutRes
//    override fun getLayoutRes(): Int = R.layout.fragment_repos
//
//    private val viewModel by lazy { ViewModelProvider(this).get(ReposViewModel::class.java) }
//
//    companion object {
//        fun newInstance() = ReposFragment()
//    }
//
//    override fun setView(view: View, savedInstanceState: Bundle?, arguments: Bundle?) {
//        Log.i(TAG, "setView")
//
//        binding.reposVM = viewModel
//    }
//}

class ReposFragment : DatabindingFragment() {

    private val viewModel by lazy { ViewModelProvider(this).get(ReposViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentReposBinding>(inflater, R.layout.fragment_repos, container).apply {
            reposVM = viewModel
            lifecycleOwner = this@ReposFragment
        }.root
    }
}