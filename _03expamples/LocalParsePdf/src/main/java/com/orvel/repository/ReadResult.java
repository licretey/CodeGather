package com.orvel.repository;

public class ReadResult {
    private Boolean status;
    private String dir;

    public ReadResult(Boolean status, String dir) {
        this.status = status;
        this.dir = dir;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
