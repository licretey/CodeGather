package com.serialization.bean;

public class UserVO<T> {
    private Boolean succes = Boolean.TRUE;
    private T data;
    private UserVO(){}

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

    public static <T> UserVO<T> buildSuccess(T t){
        UserVO<T> resultVO = new UserVO<>();
        resultVO.setData(t);
        return resultVO;
    }
}
