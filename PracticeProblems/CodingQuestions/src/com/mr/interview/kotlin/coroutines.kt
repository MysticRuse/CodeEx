package com.mr.interview.kotlin

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * https://developer.android.com/codelabs/basic-android-kotlin-compose-coroutines-kotlin-playground#0
 */
fun main() {

    // Synchronous code
    //runSynchronous()

    // Asynchronous code
    // Run with launch {} inside blocking
    //runAsynchronousWithLaunch()

    // Run with async and return values inside blocking
    //runAsynchronousWithAsync()

    // Run coroutineScope
    //runCoroutineScope()

    // Coroutine Exceptions
    //runCoroutineScopeWithErrorHandling()

    //runCoroutineScopeWithErrorHandling2()

    // Coroutine Cancellation
    //runCoroutineScopeWithCancellation()

    // Coroutine concepts
    runCoroutineConceptsWithoutContext()
    runCoroutineConceptsWithContext()

}

fun runCoroutineConceptsWithContext() {
    /**
     * If you have coroutines that were started on the main thread, and you want to move certain operations off the main thread,
     * then you can use withContext to switch the dispatcher being used for that work. Choose appropriately from the
     * available dispatchers: Main, Default, and IO depending on the type of operation it is.
     * Then that work can be assigned to a thread (or group of threads called a thread pool) designated for that purpose.
     * Coroutines can suspend themselves, and the dispatcher also influences how they resume.
     */
    runBlocking {
        println("--- Coroutine concepts with context--")
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }

    /**
     * Output should be:
     * main @coroutine#1 - runBlocking function
     * Loading...
     * main @coroutine#2 - launch function
     * DefaultDispatcher-worker-1 @coroutine#2 - withContext function
     * 10 results found.
     * main @coroutine#2 - end of launch function
     */
}

fun runCoroutineConceptsWithoutContext() {
    runBlocking {
        println("--- Coroutine concepts without context--")
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            delay(1000)
            println("10 results found.")
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }
}

fun runCoroutineScopeWithCancellation() {
    val time = measureTimeMillis {
        runBlocking {
            println("--- Weather forecast with coroutineScope with cancellation---")
            println(getWeatherReportWithCancellation())
            println("Have a good day!")
        }
    }
    println("Execution time coroutineScope with cancellation : ${time/1000.0} seconds")
}

fun runCoroutineScopeWithErrorHandling2() {
    val time = measureTimeMillis {
        runBlocking {
            println("--- Weather forecast with coroutineScope fine-grained---")
            println(getWeatherReportWithErrorHandling())
            println("Have a good day!")
        }
    }
    println("Execution time coroutineScope fine-grained error handling : ${time/1000.0} seconds")
}

fun runCoroutineScopeWithErrorHandling() {
    val time = measureTimeMillis {
        runBlocking {
            println("--- Weather forecast with coroutineScope and error handling---")
            try {
                println(getWeatherReportThrow())
            } catch (e: AssertionError) {
                println("Caught exception in runBlocking(): $e")
                println("Report unavailable at this time")
            }

            println("Have a good day!")
        }
    }
    println("Execution time coroutineScope error handling: ${time/1000.0} seconds")
}

fun runCoroutineScope() {
    // coroutineScope() will only return once all its work, including any coroutines it launched, have completed.
    // In this case, both coroutines getForecast() and getTemperature() need to finish and return their respective results.
    // Then the Sunny text and 30°C are combined and returned from the scope.
    // This weather report of Sunny 30°C gets printed to the output, and the caller can proceed to the last print statement of Have a good day!.
    // With coroutineScope(), even though the function is internally doing work concurrently, it appears to the caller
    // as a synchronous operation because coroutineScope won't return until all work is done.
    val time = measureTimeMillis {
        runBlocking {
            println("--- Weather forecast with coroutineScope---")
            println(getWeatherReport())
            println("Have a good day!")
        }
    }
    println("Execution time coroutineScope : ${time/1000.0} seconds")
}

fun runAsynchronousWithAsync() {
    // Use the async() function from the coroutines library if you care about when the coroutine finishes
    // and need a return value from it.
    // The async() function returns an object of type Deferred, which is like a promise that the result will be in there
    // when it's ready. You can access the result on the Deferred object using await().

    val time = measureTimeMillis {
        runBlocking {
            println("---Weather Forecast Async with async await deferred---")

            // after the two async() calls, you can access the result of those coroutines by calling await()
            // on the Deferred objects. In this case, you can print the value of each coroutine using
            // forecast.await() and temperature.await().
            val forecast: Deferred<String> = async { getForecast() }
            val temperature: Deferred<String> = async { getTemperature() }
            println("${forecast.await()} ${temperature.await()}")
            println("Have a good day!")

        }
    }
    println("Execution time async : ${time/1000.0} seconds")
}

fun runAsynchronousWithLaunch() {
    val time = measureTimeMillis {
        runBlocking {
            println("---Weather Forecast Async with launch --")
            // "Fire and forget" nature of Launch { }
            launch {
                printForecast()
            }
            launch {
                printTemperature()
            }
            println("Have a good day!")
        }
    }
    println("Execution time launch : ${time/1000.0} seconds")
}

fun runSynchronous() {
    val time = measureTimeMillis {
        // runBlocking is synchronous - each call in body called sequentially
        runBlocking {
            println("---Weather Forecast Sync---")
            printForecast()
            printTemperature()
            println("Have a good day!")
        }
    }
    println("Execution time sync : ${time/1000.0} seconds")
}

// coroutineScope{} creates a local scope for this weather report task
suspend fun getWeatherReport() = coroutineScope {
    val forecast: Deferred<String> = async { getForecast() }
    val temperature: Deferred<String> = async { getTemperature() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getWeatherReportThrow() = coroutineScope {
    val forecast: Deferred<String> = async { getForecast() }
    val temperature: Deferred<String> = async { getTemperatureThrow() }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getWeatherReportWithErrorHandling() = coroutineScope {
    val forecast: Deferred<String> = async { getForecast() }
    val temperature: Deferred<String> = async {
        try {
            getTemperatureThrow()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }
    "${forecast.await()} ${temperature.await()}"
}

suspend fun getWeatherReportWithCancellation() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }

    delay(200)
    temperature.cancel()

    "${forecast.await()}"
}

suspend fun getForecast() : String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    //delay(1000)
    delay(1000)
    return "30\u00b0C"
}

suspend fun getTemperatureThrow(): String {
    //delay(1000)
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}

suspend fun printForecast() {
    delay(1000)
    println("sunny")
}

suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}
