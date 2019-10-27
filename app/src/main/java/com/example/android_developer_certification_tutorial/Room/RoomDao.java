package com.example.android_developer_certification_tutorial.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {

    @Query("SELECT * FROM roomEntity")
    List<RoomEntity> getAll();

    @Query("SELECT * FROM roomEntity where first_name LIKE  :firstName")
    RoomEntity findByName(String firstName);

    @Query("SELECT COUNT(*) from roomEntity")
    int countEntities();

    @Insert
    void insertAll(RoomEntity... roomEntities);

    @Query("DELETE FROM roomEntity WHERE first_name = :userName")
    void delete(String userName);
}