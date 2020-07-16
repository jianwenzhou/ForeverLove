package com.jareven.foreverlove

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * @ClassName Main
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/14 13:55
 * @Version 1.0
 * 简介：
 */
class Main {





}



fun main(){

    test()

}


private fun test() = runBlocking {
    repeat(8) {
        println("协程执行$it 线程id：${Thread.currentThread().id}")
        delay(1000)
    }
}