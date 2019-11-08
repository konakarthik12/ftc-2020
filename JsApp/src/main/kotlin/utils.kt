import kotlin.coroutines.*
import kotlin.js.Promise


suspend fun <T> Promise<T>.await(): T = suspendCoroutine { cont ->
    then({ cont.resume(it) }, { cont.resumeWithException(it) })
}

fun launch(block: suspend () -> Unit) {
    block.startCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext get() = EmptyCoroutineContext
        override fun resumeWith(value: Result<Unit>) {}
     //   override fun resumeWithException(e: Throwable) { println("Coroutine failed: $e") }
    })
}