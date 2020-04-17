package dev.chu.toyapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import dev.chu.toyapp.data.LoadingState
import dev.chu.toyapp.data.remote.Api
import dev.chu.toyapp.data.remote.ApiService
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.extensions.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposRepository {

    var isLoading: MutableLiveData<LoadingState> = MutableLiveData()

    private val api: ApiService = Api().createService(ApiService::class.java)
    private var _listGithubRepos: MutableLiveData<List<GithubRepos>> = MutableLiveData()

    val listGithubRepos: LiveData<List<GithubRepos>>
        get() = _listGithubRepos

    fun getRepositories() {
        isLoading.postValue(LoadingState.LOADING)

        api.getRepositories()
            .enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e(TAG, "getRepositories onFailure ${t.printStackTrace()}")
                    _listGithubRepos.postValue(null)
                    isLoading.postValue(LoadingState.error(t.message))
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "getRepositories onResponse isSuccessful")

                        val it = response.body()?.getAsJsonArray("items")
                        val type = object : TypeToken<List<GithubRepos>>() {}.type
                        val result =
                            GsonBuilder().setLenient().create().fromJson(it, type) as List<GithubRepos>

                        _listGithubRepos.postValue(result)
                        isLoading.postValue(LoadingState.SUCCESS)
                    } else {
                        Log.d(TAG, "getRepositories onResponse")
                        isLoading.postValue(LoadingState.error(response.message()))
                    }
                }
            })
    }
}