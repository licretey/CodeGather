package cache

import (
	"fmt"
	"log"
	"sync"
	"time"
)

type MemCache struct {
	maxMemorySize     int64
	maxMemorySizeStr  string
	currentMemorySize int64
	// 缓存键值对
	values map[string]*memeCacheValue
	// 读写锁
	locker sync.RWMutex
	// 清除过期缓存的频率
	clearExpiredInterval time.Duration
}

// 内存数据结构
type memeCacheValue struct {
	// 缓存值
	val interface{}
	// 过期时间
	expireTime time.Time
	// 有效时长
	expireDuration time.Duration
	// value 大小
	size int64
}

func (m *MemCache) SetMaxMeory(size string) bool {
	m.maxMemorySize, m.maxMemorySizeStr = ParseSize(size)
	fmt.Println(m.maxMemorySize, m.maxMemorySizeStr)
	return true
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
// 删除后新增
func (m *MemCache) Set(key string, val interface{}, expire time.Duration) bool {
	m.locker.Lock()
	defer m.locker.Unlock()
	v := &memeCacheValue{
		val:            val,
		expireTime:     time.Now().Add(expire),
		expireDuration: expire,
		size:           GetValueSize(val),
	}
	m.del(key)
	m.add(key, v)
	if m.currentMemorySize > m.maxMemorySize {
		m.del(key)
		log.Println(fmt.Sprintf("max memory size %s", m.maxMemorySize))
		panic(fmt.Sprintf("max memory size %s", m.maxMemorySize))
	}
	return false
}

func (m *MemCache) Get(key string) (interface{}, bool) {
	m.locker.RLock()
	defer m.locker.RUnlock()
	valBody, ok := m.get(key)
	if ok {
		// 判断是否过期，过期清除后报错
		if valBody.expireDuration != 0 && valBody.expireTime.Before(time.Now()) {
			m.del(key)
			return nil, false
		}
		return valBody.val, ok
	}
	return nil, false
}

// Del delete value
func (m *MemCache) Del(key string) bool {
	m.locker.Lock()
	defer m.locker.Unlock()
	m.del(key)
	return false
}

func (m *MemCache) Exists(key string) bool {
	m.locker.RLock()
	defer m.locker.RUnlock()
	_, ok := m.values[key]
	return ok
}

// Flush clear cache
func (m *MemCache) Flush() bool {
	m.locker.Lock()
	defer m.locker.Unlock()
	m.values = make(map[string]*memeCacheValue, 0)
	m.currentMemorySize = 0
	return true
}

// Keys get all keys
func (m *MemCache) Keys() int64 {
	m.locker.RLocker()
	defer m.locker.RUnlock()
	return int64(len(m.values))
}

func (m *MemCache) clearExpiredItem() {
	// 定时触发器
	timeTicker := time.NewTimer(m.clearExpiredInterval)
	defer timeTicker.Stop()

	for {
		select {
		case <-timeTicker.C:
			for key, item := range m.values {
				if item.expireDuration == 0 || time.Now().Before(item.expireTime) {
					continue
				}

				m.locker.Lock()
				m.del(key)
				m.locker.Unlock()
			}
		}
	}
}

func NewMemCache() Cache {
	m := &MemCache{
		values:               make(map[string]*memeCacheValue),
		clearExpiredInterval: time.Second * 3,
	}
	go m.clearExpiredItem()
	return m
}

func (m *MemCache) del(key string) {
	tmp, ok := m.get(key)
	if ok && tmp != nil {
		m.currentMemorySize -= tmp.size
		delete(m.values, key)
	}
}

func (m *MemCache) add(key string, val *memeCacheValue) {
	m.values[key] = val
	m.currentMemorySize += val.size
}

func (m *MemCache) get(key string) (*memeCacheValue, bool) {
	val, ok := m.values[key]
	return val, ok
}
