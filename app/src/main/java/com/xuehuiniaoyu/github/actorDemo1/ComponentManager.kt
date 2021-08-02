package com.xuehuiniaoyu.github.actorDemo1

interface ComponentManager {
    fun register(name: String, component: Any)
    fun unregister(name: String)
    fun getComponent(name: String): Any?
}