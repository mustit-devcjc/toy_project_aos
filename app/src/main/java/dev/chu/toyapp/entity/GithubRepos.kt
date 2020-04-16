package dev.chu.toyapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class GithubRepos(
    @SerializedName("id")
    val id: Long,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("license")
    val license: License? = null,
    @SerializedName("owner")
    var owner: Owner,
    @SerializedName("open_issues")
    val openIssues: Int,
    @SerializedName("watchers")
    val watchers: Int,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("updated_at")
    var updatedAt: Date
) : Parcelable {
    constructor() : this(
        0,
        "",
        "",
        "",
        "",
        "",
        License("", "", "", "", ""),
        Owner(0, ""),
        0,
        0,
        0,
        Date()
    )
}