package cache

import "time"

type Cache interface {
	//
	SetMaxMeory(size string) bool
	// get value
	Get(key string) (interface{}, bool)
	// set value to cache with expire time (精确到秒)
	Set(key string, val interface{}, expire time.Duration) bool
	// delete value
	Del(key string) bool
	//
	Exists(key string) bool
	// clear cache
	Flush() bool
	// get all keys
	Keys() int64
}
