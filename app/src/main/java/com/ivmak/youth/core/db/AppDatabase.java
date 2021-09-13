package com.ivmak.youth.core.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ivmak.youth.core.model.User;
import com.ivmak.youth.core.model.YouthEvent;

@Database(entities = {

        User.class,
        YouthEvent.class

}, version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters({
        UserListConverter.class
})
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract MasterDao getMasterDAO();
}
