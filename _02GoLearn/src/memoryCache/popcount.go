package main

// pc[i] is the population count of i.
var pc [256]byte

func init() {
	for i := range pc {
		pc[i] = pc[i/2] + byte(i&1)
	}
}

// 计算二进制数字中1的个数
// PopCount returns the population count (number of set bits) of x.
func PopCount(x uint64) int {
	return int(pc[byte(x>>(0*8))] +
		pc[byte(x>>(1*8))] +
		pc[byte(x>>(2*8))] +
		pc[byte(x>>(3*8))] +
		pc[byte(x>>(4*8))] +
		pc[byte(x>>(5*8))] +
		pc[byte(x>>(6*8))] +
		pc[byte(x>>(7*8))])
}

//func main() {
//	//var x uint64 = 0b10101010_10101010_10101010_10101010_10101010_10101010_10101010_10101010
//	var x uint64 = 0b101010101
//
//	fmt.Println(PopCount(x))
//
//	//
//}
