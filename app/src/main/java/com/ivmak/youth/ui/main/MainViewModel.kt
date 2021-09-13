package com.ivmak.youth.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivmak.youth.core.model.User
import com.ivmak.youth.core.repo.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    val users = MutableLiveData<List<User>>()

    init {
        updateUsers()
    }

    private fun updateUsers() {
        users.postValue(repo.getUsers())
    }


    fun saveUser(user: User) {
        repo.saveUser(user)
        updateUsers()
    }

}