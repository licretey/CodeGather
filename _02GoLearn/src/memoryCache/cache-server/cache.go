package cache_server

import (
	"memoryCache/cache"
	"time"
)

/**
 * @Description: 代理模式，封装接口对Set方法进行增强
 */

// 代理结构体
type cacheServer struct {
	memeCache cache.Cache
}

func NewCacheServer() *cacheServer {
	return &cacheServer{
		memeCache: cache.NewMemCache(),
	}
}

func (cs *cacheServer) SetMaxMemory(size string) bool {
	return cs.memeCache.SetMaxMemory(size)
}

func (cs *cacheServer) Get(key string) (interface{}, bool) {
	return cs.memeCache.Get(key)
}

// 提供可传递失效时间的扩充方法
func (cs *cacheServer) Set(key string, val interface{}, expire ...time.Duration) bool {
	expireTs := time.Second * 0
	if len(expire) > 0 {
		expireTs = expire[0]
	}
	return cs.memeCache.Set(key, val, expireTs)
}

// delete value
func (cs *cacheServer) Del(key string) bool {
	return cs.memeCache.Del(key)
}

func (cs *cacheServer) Exists(key string) bool {
	return cs.memeCache.Exists(key)
}

// clear cache
func (cs *cacheServer) Flush() bool {
	return cs.memeCache.Flush()
}

// get all keys
func (cs *cacheServer) Keys() int64 {
	return cs.memeCache.Keys()
}
