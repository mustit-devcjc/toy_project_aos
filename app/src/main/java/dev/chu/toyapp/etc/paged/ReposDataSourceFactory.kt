package dev.chu.toyapp.etc.paged

import androidx.paging.DataSource
import dev.chu.toyapp.entity.GithubRepos

class ReposDataSourceFactory(private val list: List<GithubRepos>) :
    DataSource.Factory<Int, GithubRepos>() {
    override fun create(): DataSource<Int, GithubRepos> {
        return ReposDataSource(list)
    }
}