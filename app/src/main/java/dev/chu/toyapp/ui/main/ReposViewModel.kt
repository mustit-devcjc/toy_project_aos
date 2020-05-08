package dev.chu.toyapp.ui.main

import androidx.lifecycle.*
import dev.chu.datamodule.LoadingState
import dev.chu.toyapp.data.repository.ReposRepository
import dev.chu.toyapp.entity.GithubRepos

class ReposViewModel : ViewModel() {

    private val repo by lazy { ReposRepository() }

    init {
        repo.getRepositories()
    }

    val isLoading: LiveData<LoadingState>
        get() = repo.isLoading

    val githubRepos: LiveData<List<GithubRepos>>
        get() = repo.listGithubRepos

    var searchUserNameForFilter = MutableLiveData<String>()
}