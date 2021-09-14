package com.ivmak.youth.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ivmak.youth.core.model.User
import com.ivmak.youth.core.model.YouthEvent
import com.ivmak.youth.core.repo.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    val users = MutableLiveData<List<User>>()

    val curEvent = MutableLiveData<YouthEvent>()

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

    fun deleteUser(user: User) {
        repo.deleteUser(user)
    }


    fun saveEvent(event: YouthEvent) {
        repo.saveEvent(event)
    }

    fun getEvents(): LiveData<List<YouthEvent>> = repo.getEventsAsync()

    fun getEventByTs(ts: Long) {
        curEvent.postValue(repo.getEventByTs(ts))
    }

}