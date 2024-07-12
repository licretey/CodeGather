package main

import (
	"memoryCache/cache"
	"time"
)

func main() {
	cc := cache.NewMemCache()
	cc.SetMaxMeory("200B")

	cc.Set("int", 1, time.Second)
	cc.Set("bool", false, time.Second)
	cc.Set("data", map[string]interface{}{"a": 1}, time.Second)

	cc.Get("int")
	cc.Del("bool")
	cc.Del("data")
	//cc.Flush()
	//cc.Size()
	//cc.Keys()
	//cc.Close()
	//cc.IsExist("key")

	//cc.Add("key", "value", 0)

}
