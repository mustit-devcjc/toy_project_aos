package dev.chu.toyapp.common

import dev.chu.toyapp.GlobalApplication
import dev.chu.toyapp.R

object Const {
    var BASE_URL: String = GlobalApplication.getInstance().resources.getString(R.string.server_url)

    object EXTRA {
        const val USER_NAME = "USER_NAME"
        const val USER_INFO = "USER_INFO"
    }

    object ARGS {
        const val REPOS = "REPOS"
    }
}