package io.github.k_ishigaki.mezamashi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val applicationDispatcher: CoroutineDispatcher = Dispatchers.IO

internal actual val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
