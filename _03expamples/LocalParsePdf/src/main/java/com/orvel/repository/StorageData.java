package com.orvel.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 运行时需记录的信息，用于提升交互体验
 */
public class StorageData {
    @JsonProperty("lastSelectDir")
    private String lastSelectDir;

    public StorageData(String lastSelectDir) {
        this.lastSelectDir = lastSelectDir;
    }


    public StorageData() {}

    public String getLastSelectDir() {
        return lastSelectDir;
    }

    public void setLastSelectDir(String lastSelectDir) {
        this.lastSelectDir = lastSelectDir;
    }

}
