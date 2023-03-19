package com.cache;

import org.junit.Test;

import java.util.LinkedHashMap;

public interface Ehcache09SelfCache {
    void put(Object key, Object value);

    void remove(Object key);

    void clear();

    Object get(Object key);

    int size();
}
