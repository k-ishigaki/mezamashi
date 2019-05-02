package io.github.k_ishigaki.mezamashi

import kotlinx.coroutines.*
import platform.darwin.*
import kotlin.coroutines.CoroutineContext

internal actual val applicationDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

internal actual val defaultDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_global_queue(
    DISPATCH_QUEUE_PRIORITY_DEFAULT, 0))

internal class NsQueueDispatcher(
    private val dispatchQueue: dispatch_queue_t
) : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}
