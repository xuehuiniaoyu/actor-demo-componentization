package com.xuehuiniaoyu.github.actorDemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.xuehuiniaoyu.github.actorDemo1.component.ComponentMain
import com.xuehuiniaoyu.github.actorDemo1.logger.Logger

class MainActivity : AppCompatActivity() {
    private val components by lazy {
        (applicationContext as MyApplication).components
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerComponents()
        bindEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        components.unregister("logger")
        components.unregister("component")
    }

    private fun registerComponents() {
        components.register("logger", Logger())
        components.register("component", ComponentMain(this))
    }

    private fun bindEvents() {
        findViewById<Button>(R.id.btn_component).setOnClickListener {
            val c = components.getComponent("component") as ComponentMain
            c.load()
        }
    }
}