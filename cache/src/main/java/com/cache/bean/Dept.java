package com.cache.bean;

public class Dept {
    // 测试大对象占用内存空间场景
    private Long id;
    private byte[] bytes = new byte[1024*1024]; //1Mb

    public Dept(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * jvm回收该对象时，会回调此方法，完成自定义资源清理或自救
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println(id + "将要被回收，赶紧想办法自救");
        super.finalize();
    }
}
