package dev.chu.toyapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import dev.chu.toyapp.data.remote.Api
import dev.chu.toyapp.data.remote.ApiService
import dev.chu.toyapp.entity.GithubRepos
import dev.chu.toyapp.etc.extensions.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserReposRepository {
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val api: ApiService = Api().createService(ApiService::class.java)
    private var _listUserRepos: MutableLiveData<List<GithubRepos>> = MutableLiveData()

    val listUserRepos: LiveData<List<GithubRepos>>
        get() = _listUserRepos

    fun getUserRepos(user: String) {
        isLoading.value = true

        api.getUserRepos(user)
            .enqueue(object : Callback<JsonArray> {
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.e(TAG, "getUserRepos onFailure")
                    t.printStackTrace()
                    _listUserRepos.postValue(null)
                    isLoading.value = false
                }

                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "getUserRepos onResponse isSuccessful")

                        val it = response.body()
                        val type = object : TypeToken<List<GithubRepos>>() {}.type
                        val result =
                            GsonBuilder().setLenient().create().fromJson(it, type) as List<GithubRepos>

                        _listUserRepos.postValue(result)
                    } else {
                        Log.d(TAG, "getUserRepos onResponse")
                    }
                    isLoading.value = false
                }
            })
    }
}
