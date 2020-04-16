package dev.chu.toyapp.etc.paged

import android.util.Log
import androidx.paging.PositionalDataSource
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.extensions.TAG

class ReposDataSource(private val list: List<GithubRepos>) :
    PositionalDataSource<GithubRepos>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<GithubRepos>
    ) {
        Log.i(TAG, "Initial Loading, start(offset): ${params.requestedStartPosition}, size(limit): ${params.requestedLoadSize}")
        callback.onResult(getItems(params.requestedStartPosition, params.requestedLoadSize, list), 0)
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<GithubRepos>
    ) {
        Log.i(TAG, "loadRange start(offset): ${params.startPosition}, size(limit) : ${params.loadSize}")
        callback.onResult(getItems(params.startPosition, params.loadSize, list))
    }

    private fun getItems(
        offset: Int,
        limit: Int,
        listRepos: List<GithubRepos>
    ): MutableList<GithubRepos> {
        Log.w(TAG, "offset = $offset, limit = $limit")
        val list: MutableList<GithubRepos> = mutableListOf()

        if(offset != 100) {
            for(i in offset until limit+offset) {
                list.add(listRepos[i])
            }
        }


        return list
    }
}