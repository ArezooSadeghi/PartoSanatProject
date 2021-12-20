package com.example.partosanatproject.model;

import com.example.partosanatproject.R;

import tellh.com.recyclertreeview_lib.LayoutItemType;

public class File implements LayoutItemType {
    private String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.file_item;
    }
}
