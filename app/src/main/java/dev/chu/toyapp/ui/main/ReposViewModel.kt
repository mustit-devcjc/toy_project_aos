package dev.chu.toyapp.ui.main

import androidx.lifecycle.*
import dev.chu.toyapp.data.repository.ReposRepository
import dev.chu.toyapp.entity.GithubRepos

class ReposViewModel : ViewModel() {

    private val repo by lazy { ReposRepository() }

    init {
        repo.getRepositories()
    }

    val isLoading: LiveData<Boolean>
        get() = repo.isLoading

    val githubRepos: LiveData<List<GithubRepos>>
        get() = repo.listGithubRepos

//    val getRepos: LiveData<PagedList<GithubRepositories>> = Transformations
//        .switchMap(_isData) {
//            if(!it) {
//                MutableLiveData()
//            } else {
//                getRepos(_githubRepos.value!!)
//            }
//        }
//
//    fun setIsData(isData: Boolean) {
//        _isData.value = isData
//    }
//
//    private fun getRepos(list: List<GithubRepositories>) : LiveData<PagedList<GithubRepositories>> {
//        val dataSourceFactory = ReposDataSourceFactory(list)
//        val pagedListConfig = PagedList.Config.Builder()
//            .setPageSize(20)
//            .setInitialLoadSizeHint(60)
//            .setEnablePlaceholders(false)
//            .build()
//
//        return LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
//    }
}