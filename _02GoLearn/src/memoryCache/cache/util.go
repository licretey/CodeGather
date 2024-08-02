package cache

import (
	"encoding/json"
	"log"
	"regexp"
	"strconv"
	"strings"
)

const (
	B = 1 << (iota * 10)
	KB
	MB
	GB
	TB
	PB
)

func ParseSize(size string) (int64, string) {
	re, _ := regexp.Compile("[0-9]+")
	// 将字符串中的数字全部替换为空
	unit := string(re.ReplaceAll([]byte(size), []byte("")))
	// 将字符串中的数字替换出来后，转为int64
	num, _ := strconv.ParseInt(strings.Replace(size, unit, "", 1), 10, 64)
	unit = strings.ToUpper(unit)
	var byteNum int64 = 0

	switch unit {
	case "B":
		byteNum = num
	case "K":
		byteNum = num * KB
	case "M":
		byteNum = num * MB
	case "G":
		byteNum = num * GB
	case "T":
		byteNum = num * TB
	case "P":
		byteNum = num * PB
	default:
		num = 0
	}

	if num == 0 {
		log.Println("ParseSize only accept B,KB,MB,GB,TB,PB")
		num = 100
		unit = "MB"
		byteNum = num * MB
	}
	sizeStr := strconv.FormatInt(num, 10) + unit
	return byteNum, sizeStr
}

func GetValueSize(val interface{}) int64 {
	// unsafe.Sizeof(val) 存储方式不同，返回值不同，但因为是interface{}所以相同
	// 野路子：利用序列化json时已确定了类型，从而反序列化获取大小(序列化多添加了"':等符号，可以替换掉再计算)
	bytes, _ := json.Marshal(val)
	size := int64(len(bytes))
	return size
}
