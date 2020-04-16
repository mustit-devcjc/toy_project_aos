package dev.chu.toyapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dev.chu.toyapp.R
import dev.chu.toyapp.base.BaseFragment
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.etc.extensions.TAG
import dev.chu.toyapp.ui.repo_detail.RepoDetailActivity
import dev.chu.toyapp.ui.main.adapter.UsersAdapter
import dev.chu.toyapp.ui.user_repos.UserReposActivity

class UsersFragment : BaseFragment() {
    @LayoutRes
    override fun getLayoutRes(): Int = R.layout.fragment_users

    private val viewModel by lazy { ViewModelProvider(this).get(UsersViewModel::class.java) }

    private lateinit var adapter: UsersAdapter
    private lateinit var usersEt: EditText
    private lateinit var usersIv: ImageView
    private lateinit var usersRv: RecyclerView
    private lateinit var usersPb: ProgressBar

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun setView(view: View, savedInstanceState: Bundle?, arguments: Bundle?) {
        Log.i(TAG, "setView")

        // region findViewById
        usersEt = view.findViewById(R.id.users_et)
        usersIv = view.findViewById(R.id.users_iv)
        usersRv = view.findViewById(R.id.users_rv)
        usersPb = view.findViewById(R.id.users_pb)
        // endregion

        onClickEvent()
        setRecyclerView()
        observeViewModel()
    }

    private fun onClickEvent() {
        usersIv.setOnClickListener { viewModel.getUsers(usersEt.text.toString()) }
    }

    private fun setRecyclerView() {
        adapter =
            UsersAdapter(mutableListOf()) { repos ->
                startActivity(Intent(context, UserReposActivity::class.java).apply {
                    putExtra(Const.EXTRA.USER_NAME, repos.login)
                })
            }
        usersRv.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            usersPb.visibility = if (it) View.VISIBLE else View.GONE
        })


        viewModel.listUsers.observe(viewLifecycleOwner, Observer {
            adapter.setNewItems(it)
        })
    }
}