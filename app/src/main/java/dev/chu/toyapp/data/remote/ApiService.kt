package dev.chu.toyapp.data.remote

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Main : GitHub Star Top 100 Repos
    // ?q=stars:%3E1&sort=stars&per_page=10&page=1
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") q: String = "stars:>1",
        @Query("sort") sort: String = "stars",
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1
    ): Call<JsonObject>

    @GET("search/repositories")
    fun getRepositories(
        @Query("q") q: String
    ): Call<JsonObject>

    // Search : GitHub UserInfo
    // ?q={user}
    // "검색한 이름(q)"이 포함된 "유저들 정보" 가져오기
    @GET("search/users")
    fun getSearchUsers(@Query("q") q: String) : Call<JsonObject>

    // Detail Result : Github User Search
    // 검색한 유저들 중 선택한 유저의 "repositories" 가져오기
    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String): Call<JsonArray>
}