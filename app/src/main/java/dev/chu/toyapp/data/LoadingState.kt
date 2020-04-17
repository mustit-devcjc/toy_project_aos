package dev.chu.toyapp.data

data class LoadingState constructor(val status: Status, val msg: String? = null) {
    companion object {
        val SUCCESS = LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.LOADING)
        fun error(msg: String?) = LoadingState(Status.FAILED, msg)
    }

    enum class Status {
        SUCCESS,
        LOADING,
        FAILED
    }
}