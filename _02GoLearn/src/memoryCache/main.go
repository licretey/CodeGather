package main

import (
	"memoryCache/cache"
	"time"
)

func main() {
	cc := cache.NewMemCache()
	cc.SetMaxMeory("2G")

	cc.Set("int", 1, time.Second)
	cc.Set("bool", false, time.Second)

	cc.Get("int")
	cc.Del("bool")
	cc.Del("data")
	cc.Flush()
	//cc.Keys()
	cc.Exists("key")

}
