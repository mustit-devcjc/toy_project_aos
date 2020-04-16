package dev.chu.toyapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @SerializedName("id")
    var id: Long,
    @SerializedName("avatar_url")
    var avatarUrl: String
) : Parcelable