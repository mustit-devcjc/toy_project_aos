package dev.chu.toyapp.ui.user_repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.chu.toyapp.data.repository.UserReposRepository
import dev.chu.toyapp.entity.GithubRepos

class UserReposViewModel : ViewModel() {
    private val repo by lazy { UserReposRepository() }

    val listUserRepos: LiveData<List<GithubRepos>>
        get() = repo.listUserRepos

    fun getUserRepos(user: String) = repo.getUserRepos(user)
}