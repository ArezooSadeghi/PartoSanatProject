package com.example.partosanatproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.partosanatproject.model.ServerData;

import java.util.List;

@Dao
public interface ServerDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ServerData serverData);

    @Query("DELETE FROM server_data_table WHERE centerName LIKE :centerName")
    void delete(String centerName);

    @Query("SELECT * FROM server_data_table")
    LiveData<List<ServerData>> getServerDataList();

    @Query("SELECT * FROM server_data_table WHERE centerName LIKE :centerName")
    ServerData getServerData(String centerName);
}
