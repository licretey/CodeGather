package main

import (
	"fmt"
	"time"
)

func main() {
	start := time.Now()
	c := make(chan int, 0) //创建无缓冲的通道 c

	//内置函数 len 返回未被读取的缓冲元素数量，cap 返回缓冲区大小
	fmt.Printf("len(c)=%d, cap(c)=%d\n", len(c), cap(c))

	go func() {
		defer fmt.Println("子go程结束")

		for i := 0; i < 3; i++ {
			c <- i
			fmt.Printf("子go程正在运行[%d]: len(c)=%d, cap(c)=%d\n", i, len(c), cap(c))
		}
	}()

	time.Sleep(2 * time.Second) //延时2s

	for i := 0; i < 3; i++ {
		num := <-c //从c中接收数据，并赋值给num
		fmt.Println("num = ", num)
	}

	fmt.Printf("main进程结束 %.2fs elapsed\n", time.Since(start).Seconds())

}

// 第1次执行结果
// len(c)=0, cap(c)=0
// 子go程正在运行[0]: len(c)=0, cap(c)=0
// num =  0
// num =  1
// 子go程正在运行[1]: len(c)=0, cap(c)=0
// 子go程正在运行[2]: len(c)=0, cap(c)=0
// 子go程结束
// num =  2
// main进程结束 2.01s elapsed
//
// 第2次执行结果
// len(c)=0, cap(c)=0
// num =  0
// 子go程正在运行[0]: len(c)=0, cap(c)=0
// 子go程正在运行[1]: len(c)=0, cap(c)=0
// num =  1
// num =  2
// main进程结束 2.01s elapsed
