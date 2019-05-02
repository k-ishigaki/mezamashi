package com.splascope.mezamashi

import kotlinx.coroutines.*

expect fun platformName(): String

fun createApplicationScreenMessage() : String {
    return "Kotlin Rocks on ${platformName()}"
}

fun callDelayed(body : () -> Unit) {
    GlobalScope.launch(applicationDispatcher, CoroutineStart.DEFAULT) {
        println("timer start")
        delay(1000)
        println("timer end")
        body()
    }
}
