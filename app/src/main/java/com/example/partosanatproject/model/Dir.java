package com.example.partosanatproject.model;

import com.example.partosanatproject.R;

import tellh.com.recyclertreeview_lib.LayoutItemType;

public class Dir implements LayoutItemType {
    private String dirName;
    private int dirID;

    public Dir(String dirName, int dirID) {
        this.dirName = dirName;
        this.dirID = dirID;
    }

    public String getDirName() {
        return dirName;
    }

    public int getDirID() {
        return dirID;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dir_item;
    }
}