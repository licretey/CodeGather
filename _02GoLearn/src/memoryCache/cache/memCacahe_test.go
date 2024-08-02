package cache

import (
	"testing"
	"time"
)

func TestCacheOP(t *testing.T) {
	testData := []struct {
		key    string
		value  interface{}
		expire time.Duration
	}{
		{"key1", "678value1", time.Second * 10},
		{"key2", false, time.Second * 11},
		{"key3", true, time.Second * 12},
		{"key4", map[string]interface{}{"a": 3, "b": false}, time.Second * 13},
		{"key5", "abccddea", time.Second * 14},
		{"key6", "天下行文的啊发发", time.Second * 15},
	}

	c := NewMemCache()
	c.SetMaxMemory("10MB")
	for _, data := range testData {
		// 设置缓存
		c.Set(data.key, data.value, data.expire)
		// 获取缓存
		val, ok := c.Get(data.key)
		if !ok {
			t.Errorf("Get error: %v", data.key)
		}
		if data.key != "key4" && val != data.value {
			t.Errorf("Get cache value isn't same: %v value %v", data.key, val)
		}

		// 比较map
		_, ok2 := val.(map[string]interface{})
		if data.key == "key4" && !ok2 {
			t.Errorf("Get cache value isn't same: %v value %v", data.key, val)
		}
	}

	if int64(len(testData)) != c.Keys() {
		t.Errorf("Cache size uncorrect!")
	}
	c.Del(testData[0].key)
	c.Del(testData[1].key)
	if int64(len(testData)) != c.Keys()+2 {
		t.Errorf("Cache size uncorrect!")
	}

	time.Sleep(time.Second * 16)
	if c.Keys() != 0 {
		t.Errorf("Cache clear uncorrect!")
	}
}
