package com.ivmak.youth.core.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ivmak.youth.core.model.User
import com.ivmak.youth.core.model.YouthEvent

@Dao
interface MasterDao {

    // USERS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(user: User)

    @Query("SELECT * FROM user ORDER BY name")
    fun loadAllUsers(): List<User>

    @Delete
    fun deleteUser(user: User)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(youthEvent: YouthEvent)

    @Query("SELECT * FROM events ORDER BY timestamp")
    fun loadAllEvents(): List<YouthEvent>

    @Query("SELECT * FROM events ORDER BY timestamp")
    fun loadEventsAsync(): LiveData<List<YouthEvent>>

    @Query("SELECT * FROM events WHERE timestamp = :ts")
    fun loadEventByTs(ts: Long): YouthEvent
}