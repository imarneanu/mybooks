package com.imarneanu.mybooks.core.android

fun interface ErrorHandler {
    /**
     * A callback triggered when an exception is thrown inside a coroutine.
     *
     * @return true if the exception is handled, false otherwise.
     */
    operator fun invoke(throwable: Throwable): Boolean
}
