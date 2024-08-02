package main

import (
	cache_server "memoryCache/cache-server"
	"time"
)

func main() {
	cc := cache_server.NewCacheServer()
	cc.SetMaxMemory("200M")

	cc.Set("int", 1, time.Second)
	cc.Set("bool", false, time.Second)
	cc.Set("data", map[string]interface{}{"a": 1}, time.Second)
	cc.Set("int", 1)
	cc.Set("bool", false)
	cc.Set("data", map[string]interface{}{"a": 1})

	cc.Get("int")
	cc.Del("bool")
	cc.Del("data")
	cc.Flush()
	//cc.Keys()
	cc.Exists("key")

}
