package com.ivmak.youth.core.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ivmak.youth.core.model.User;

@Database(entities = {

        User.class

}, version = AppDatabase.VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract MasterDao getMasterDAO();
}
