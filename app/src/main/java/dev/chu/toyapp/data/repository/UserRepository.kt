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
import dev.chu.toyapp.entity.Users
import dev.chu.toyapp.etc.extensions.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    var isLoading: MutableLiveData<LoadingState> = MutableLiveData(LoadingState.SUCCESS)

    private val api: ApiService = Api().createService(ApiService::class.java)
    private var _listUsers: MutableLiveData<List<Users>> = MutableLiveData()

    val listUsers: LiveData<List<Users>>
        get() = _listUsers

    fun getSearchUsers(q: String) {
        isLoading.postValue(LoadingState.LOADING)

        api.getSearchUsers(q)
            .enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e(TAG, "getSearchUsers onFailure ${t.printStackTrace()}")
                    _listUsers.postValue(null)
                    isLoading.postValue(LoadingState.error(t.message))
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "getSearchUsers onResponse isSuccessful")

                        val it = response.body()?.getAsJsonArray("items")
                        val type = object : TypeToken<List<Users>>() {}.type
                        val result =
                            GsonBuilder().setLenient().create().fromJson(it, type) as List<Users>

                        _listUsers.postValue(result)
                        isLoading.postValue(LoadingState.SUCCESS)
                    } else {
                        Log.d(TAG, "getSearchUsers onResponse")
                        isLoading.postValue(LoadingState.error(response.message()))
                    }
                }
            })
    }
}