package com.xuehuiniaoyu.github.actorDemo1.logger

import android.util.Log

class Logger {
    fun logI(tag: String, log: Any) {
        Log.i(tag, log.toString())
    }

    fun logE(tag: String, log: Any) {
        Log.e(tag, log.toString())
    }
}