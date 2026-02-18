package com.mr.interview.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun helloWord() {

    // producer
    val flow = flow {
        for ( i in 1..20) {
            emit(i)
            delay(1000L)
        }
    }

    // Consumer
    GlobalScope.launch {
        flow.buffer().filter {
            it %2 == 0
        }.map {
            it * it
        }.collect {
            println(it)
            delay(2000)
        }
    }
    //return "hello word"

    // Producers and Consumers of flow.
}

fun main() = runBlocking {
    helloWord()
}