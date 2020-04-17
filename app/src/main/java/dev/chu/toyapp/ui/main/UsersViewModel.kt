package dev.chu.toyapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.chu.toyapp.data.LoadingState
import dev.chu.toyapp.data.repository.UserRepository
import dev.chu.toyapp.entity.Users

class UsersViewModel : ViewModel() {

    private val repo by lazy { UserRepository() }

    val isLoading: LiveData<LoadingState>
        get() = repo.isLoading

    val listUsers: LiveData<List<Users>>
        get() = repo.listUsers

    fun getUsers(q: String) = repo.getSearchUsers(q)
    fun getUsers() = repo.getSearchUsers(searchUserName.value.toString())

    var searchUserName = MutableLiveData<String>()
}