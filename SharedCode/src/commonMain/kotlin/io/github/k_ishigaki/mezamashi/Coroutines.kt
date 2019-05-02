package io.github.k_ishigaki.mezamashi

import kotlinx.coroutines.CoroutineDispatcher

internal expect val applicationDispatcher: CoroutineDispatcher

internal expect val defaultDispatcher: CoroutineDispatcher