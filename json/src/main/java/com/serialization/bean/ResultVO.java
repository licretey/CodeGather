package com.serialization.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultVO<T> {
    private Boolean succes = Boolean.TRUE;
    private T data;
    private ResultVO(){}

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResultVO<T> buildSuccess(T t){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(t);
        return resultVO;
    }
}
