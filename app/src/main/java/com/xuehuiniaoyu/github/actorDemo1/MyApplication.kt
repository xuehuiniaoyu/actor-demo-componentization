package com.xuehuiniaoyu.github.actorDemo1

import android.app.Application
import xuehuiniaoyu.github.io.actor.Actor

class MyApplication: Application() {
    interface ComponentLife {
        fun onCreate()
        fun onDestroy()
    }

    val components: ComponentManager by lazy {
        object: ComponentManager, HashMap<String, Any>() {
            override fun register(name: String, component: Any) {
                this[name] = component
                try {
                    proxyComponent(component).onCreate()
                } catch (e: NoSuchMethodError) {}
            }

            override fun unregister(name: String) {
                try {
                    proxyComponent(getComponent(name) ?: error("$name is null!")).onDestroy()
                } catch (e: NoSuchMethodError) {}
                remove(name)
            }

            override fun getComponent(name: String): Any? {
                return this[name]
            }
        }
    }

    fun proxyComponent(component: Any) = Actor(component).imitate(ComponentLife::class.java)
}