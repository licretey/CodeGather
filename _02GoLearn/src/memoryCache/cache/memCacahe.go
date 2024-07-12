package cache

import (
	"fmt"
	"sync"
	"time"
)

type MemCache struct {
	maxMemorySize     int64
	maxMemorySizeStr  string
	currentMemorySize int64
	// 缓存键值对
	values map[string]interface{}
	// 锁
	locker sync.RWMutex
}

type memeCacheValue struct {
	// 缓存值
	val interface{}
	// 过期时间
	expireTime time.Time
	// valye 大小
	size int64
}

func (m *MemCache) SetMaxMeory(size string) bool {
	m.maxMemorySize, m.maxMemorySizeStr = ParseSize(size)

	fmt.Println("set max memory")
	fmt.Println(m.maxMemorySize, m.maxMemorySizeStr)
	return false
}

/*
*
  - 设置缓存值
  - @param key 缓存键
  - @param val 缓存值
  - @param expire 缓存过期时间
  - @return bool
  - 获取锁后，将多个参数构建为内存值结构，
*/
func (m *MemCache) Set(key string, val interface{}, expire time.Duration) bool {
	fmt.Println("set value")
	v := &memeCacheValue{
		val:        val,
		expireTime: time.Now().Add(expire),
		size:       GetValueSize(val),
	}
	m.values[key] = v
	return false
}

func (m *MemCache) Get(key string) (interface{}, bool) {
	fmt.Println("get value")
	return nil, false
}

// Del delete value
func (m *MemCache) Del(key string) bool {
	return false
}

func (m *MemCache) Exists(key string) bool {
	return false
}

// Flush clear cache
func (m *MemCache) Flush() bool {
	return false
}

// Keys get all keys
func (m *MemCache) Keys() int64 {
	return 0
}

func NewMemCache() Cache {
	return &MemCache{}
}
