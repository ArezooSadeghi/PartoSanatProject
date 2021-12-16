package com.example.partosanatproject.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "server_data_table")
public class ServerData {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int ID;
    private String centerName;
    private String ipAddress;
    private String port;

    public ServerData(String centerName, String ipAddress, String port) {
        this.centerName = centerName;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
