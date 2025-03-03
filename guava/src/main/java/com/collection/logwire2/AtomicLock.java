package com.collection.logwire2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description:
 * @Date: 2025/2/12 10:18
 */
public class AtomicLock {
    private final AtomicBoolean flag = new AtomicBoolean();

    public void lock() {
        for (; !flag.compareAndSet(false, true); ) {
            // do nothing
        }
    }

    public void unlock() {
        flag.set(false);
    }
}
