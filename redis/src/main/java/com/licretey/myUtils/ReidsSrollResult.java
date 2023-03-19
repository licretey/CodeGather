package com.licretey.myUtils;

import java.util.List;

/**
 * 工具类：使用Reidis做滚动分页
 */
public class ReidsSrollResult {
    private List<?> list;
    private long minTime;
    private Integer offset;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
