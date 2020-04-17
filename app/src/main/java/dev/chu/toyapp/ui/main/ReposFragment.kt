package dev.chu.toyapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseFragment
import dev.chu.toyapp.data.LoadingState
import dev.chu.toyapp.etc.extensions.TAG
import dev.chu.toyapp.ui.main.adapter.ReposAdapter
import dev.chu.toyapp.ui.repo_detail.RepoDetailActivity

class ReposFragment : BaseFragment() {
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_repos

    private val viewModel by lazy { ViewModelProvider(this).get(ReposViewModel::class.java) }

    private lateinit var adapter: ReposAdapter
    private lateinit var reposEt: EditText
    private lateinit var reposIv: ImageView
    private lateinit var reposRv: RecyclerView
    private lateinit var reposPb: ProgressBar

    companion object {
        fun newInstance() = ReposFragment()
    }

    override fun setView(view: View, savedInstanceState: Bundle?, arguments: Bundle?) {
        Log.i(TAG, "setView")

        // region findViewById
        reposEt = view.findViewById(R.id.repos_et)
        reposIv = view.findViewById(R.id.repos_iv)
        reposRv = view.findViewById(R.id.repos_rv)
        reposPb = view.findViewById(R.id.repos_pb)
        // endregion

        reposEt.addTextChangedListener {
            adapter.filter(it.toString())
        }

        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView() {
        adapter = ReposAdapter(mutableListOf()) { repos ->
            startActivity(RepoDetailActivity.newIntent(context!!, repos))
        }
        reposRv.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            reposPb.visibility = when(it.status) {
                LoadingState.Status.SUCCESS -> View.GONE
                LoadingState.Status.LOADING -> View.VISIBLE
                LoadingState.Status.FAILED -> View.GONE
            }
        })

        viewModel.githubRepos.observe(viewLifecycleOwner, Observer {
            adapter.setNewItems(it)
        })

//        viewModel.getRepos.observe(viewLifecycleOwner, Observer {
////            adapter.submitList(it)
//            adapter.setNewItems(it)
//        })
    }
}