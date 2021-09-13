package com.ivmak.youth.core.repo

import androidx.lifecycle.LiveData
import com.ivmak.youth.core.db.MasterDao
import com.ivmak.youth.core.model.User
import com.ivmak.youth.core.model.YouthEvent
import javax.inject.Inject

class Repository @Inject constructor(private val masterDao: MasterDao) {

    fun saveUser(user: User) {
        masterDao.save(user)
    }

    fun getUsers(): List<User> {
        return masterDao.loadAllUsers()
    }

    fun deleteUser(user: User) {
        masterDao.deleteUser(user)
    }


    fun saveEvent(youthEvent: YouthEvent) {
        masterDao.save(youthEvent)
    }

    fun getEventsAsync(): LiveData<List<YouthEvent>> {
        return masterDao.loadEventsAsync()
    }

    fun getEventByTs(ts: Long): YouthEvent {
        return masterDao.loadEventByTs(ts)
    }

}