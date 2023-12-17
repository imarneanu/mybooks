package com.imarneanu.mybooks.core.android

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface ErrorHandlingViewModel<Error : Any> : ErrorReceiver<Error>, ErrorPublisher<Error>

interface ErrorReceiver<Error> {
    fun receive(error: Error)
}

interface ErrorPublisher<Error> {
    fun errors(): Flow<Error>
}

class ErrorHandlingViewModelImpl<Error : Any> : ErrorHandlingViewModel<Error> {

    // TODO: Make Error a state similar to ViewState
    private val errorEvents = MutableSharedFlow<Error>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun receive(error: Error) {
        errorEvents.tryEmit(error)
    }

    override fun errors(): Flow<Error> = errorEvents
}
