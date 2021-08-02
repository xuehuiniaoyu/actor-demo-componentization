package com.xuehuiniaoyu.github.actorDemo1.component

import android.content.Context
import xuehuiniaoyu.github.io.actor.Actor
import xuehuiniaoyu.github.io.actor.ActorBean

class ComponentMain(context: Context) {
    companion object {
        const val TAG = "ComponentMain"
    }

    // define
    interface ComponentManagerProxy {
        fun getComponent(name: String): Any
    }

    interface LoggerProxy {
        fun logI(tag: String, log: Any)
        fun logE(tag: String, log: Any)
    }

    // implements
    private val components: Any by lazy {
        ActorBean(context.applicationContext).get("components") as? Any ?: error("")
    }

    private val logger by lazy {
        Actor(
            Actor(components).imitator(ComponentManagerProxy::class.java).getComponent("logger")
        ).imitator(LoggerProxy::class.java)
    }

    fun load() {
        logger.logI(TAG, "onLoad...")
        logger.logE(TAG, "onLoad...")
    }
}