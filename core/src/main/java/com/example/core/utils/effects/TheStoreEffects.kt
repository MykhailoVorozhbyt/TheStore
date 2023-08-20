package com.example.core.utils.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.DisposableEffectScope


@Composable
fun TheStoreDisposableEffect(
    key1: Any?,
    doSomething: () -> Unit,
    dispose: DisposableEffectScope.() -> DisposableEffectResult
) {
    DisposableEffect(key1) {
        doSomething.invoke()
        onDispose { dispose }
    }
}