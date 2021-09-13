package com.ivmak.youth.core.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ivmak.youth.core.model.User;

import java.lang.reflect.Type;
import java.util.List;


public class UserListConverter {

    @TypeConverter
    public String fromList(List<User> users) {
        if (users == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        return gson.toJson(users, type);
    }

    @TypeConverter
    public List<User> toList(String users) {
        if (users == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        return gson.fromJson(users, type);
    }

}