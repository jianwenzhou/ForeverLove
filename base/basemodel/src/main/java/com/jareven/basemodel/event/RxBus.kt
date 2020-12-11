package com.jareven.basemodel.event


import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * @ClassName RxBus
 * @Description TODO
 * @Author zjw
 * @Date 2020/11/24 19:19
 * @Version 1.0
 * 简介：基于Rxjava的事件总线
 */
class RxBus {

    private val bus: Subject<Any> = PublishSubject.create<Any>().toSerialized()

    private object RxBusHolder {
        val mInstance = RxBus()
    }


    /**
     * 发送消息
     */
    fun post(o: Any) {
        bus.onNext(o)
    }

    /**
     * 接受消息
     */
    fun <T> toObservable(eventType: Class<T>): Observable<*> {
        return bus.ofType(eventType)
    }

    companion object {

        /**
         * 单例模式
         */
        val instance: RxBus
            get() = RxBusHolder.mInstance
    }

}
