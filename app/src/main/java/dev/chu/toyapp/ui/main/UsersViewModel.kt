package dev.chu.toyapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.chu.toyapp.data.repository.UserRepository
import dev.chu.toyapp.entity.Users

class UsersViewModel : ViewModel() {

    private val repo by lazy { UserRepository() }

    val isLoading: LiveData<Boolean>
        get() = repo.isLoading

    val listUsers: LiveData<List<Users>>
        get() = repo.listUsers

    fun getUsers(q: String) = repo.getSearchUsers(q)
}