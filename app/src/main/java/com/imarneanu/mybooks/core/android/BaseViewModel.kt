package com.imarneanu.mybooks.core.android

import androidx.annotation.CheckResult
import androidx.lifecycle.ViewModel
import com.imarneanu.mybooks.core.coroutines.safeCollect
import com.imarneanu.mybooks.core.coroutines.safeCoroutineExceptionHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import timber.log.Timber
import kotlin.reflect.KClass

private const val LOG_QUERY_ERROR_MAX_CHAR = 300

open class BaseViewModel<BaseViewState : Any, Error : Any>(
    private val errorHandlingViewModel: ErrorHandlingViewModel<Error> = ErrorHandlingViewModelImpl(),
) : ViewModel(),
    ErrorPublisher<Error> by errorHandlingViewModel {

    private val viewStateMap = mutableMapOf<KClass<BaseViewState>, Flow<BaseViewState>>()
    private val job = SupervisorJob()
    protected val bgScope: CoroutineScope = CoroutineScope(Dispatchers.IO + job)

    private val className = this::class.simpleName

    init {
        Timber.d("Init $className")
    }

    fun viewStates(): Collection<Flow<BaseViewState>> = viewStateMap.values

    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    protected inline fun <reified T : BaseViewState> query(noinline flowFactory: () -> Flow<T>) =
        query(T::class as KClass<BaseViewState>, flowFactory as () -> Flow<BaseViewState>)

    protected fun runCommand(
        completable: suspend () -> Unit,
    ) = buildCommand(completable).run()

    protected fun buildCommand(
        completable: suspend () -> Unit,
    ): CommandBuilder = CommandBuilder(completable)

    override fun onCleared() {
        Timber.d("Deinit $className")
        job.cancelChildren()
        super.onCleared()
    }

    @Deprecated("Internal usage only! Visible because of inlining")
    protected fun query(viewStateClass: KClass<BaseViewState>, flowFactory: () -> Flow<BaseViewState>) {
        if (viewStateMap.contains(viewStateClass)) throw IllegalArgumentException("Flow<${viewStateClass.simpleName}> already registered")

        val viewStatePublisher: MutableSharedFlow<BaseViewState> = MutableSharedFlow(
            replay = 1,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
        viewStateMap[viewStateClass] = viewStatePublisher

        val baseStateName = viewStateClass.qualifiedName!!.split('.').takeLast(2)[0]

        bgScope.launch(
            safeCoroutineExceptionHandler { _, throwable ->
                Timber.e(throwable, "Query ${viewStateClass.qualifiedName} failed")
            }
        ) {
            flowFactory()
                .onEach { Timber.d("New emission of $baseStateName.${it.toString().take(
                    LOG_QUERY_ERROR_MAX_CHAR
                )}") }
                .safeCollect(viewStatePublisher::tryEmit)
        }
    }

    protected fun emitError(error: Error) = errorHandlingViewModel.receive(error)

    inner class CommandBuilder(
        val completable: suspend () -> Unit,
    ) {
        private val defaultErrorHandler: ErrorHandler = ErrorHandler { throwable ->
            Timber.e(throwable, "Executing ${completable::class} in $className failed")
            true
        }

        private val errorHandlers: MutableList<ErrorHandler> = mutableListOf(defaultErrorHandler)

        fun run() {
            bgScope.launch(CoroutineExceptionHandler { _, throwable ->
                errorHandlers.runWhile { !it(throwable) }
            }) {
                Timber.d("Executing command in ${this@BaseViewModel::class.simpleName} ${completable::class}")
                completable()
            }
        }

        private inline fun <T> Iterable<T>.runWhile(predicate: (T) -> Boolean) {
            for (item in this) {
                if (!predicate(item)) break
            }
        }

        /**
         * Adds an [ErrorHandler] which will be called in order in which it is added.
         *
         * e.g.
         *
         * ErrorHandler.Network - handles UnknownHostException
         *
         * ErrorHandler.General - handles Exception
         * ```kotlin
         * runCommand {
         *     // Do Something
         * }
         *     .attachErrorHandler(ErrorHandler.Network)
         *     .attachErrorHandler(ErrorHandler.General)
         *```
         * First it calls the Network ErrorHandler and then if the exception is not handled, the General ErrorHandler.
         * You probably noticed that **ordering matters**.
         *
         * **You should always, reading top-down, go from specific to more generic exception handlers.**
         *
         * In the example above, if the order was reversed, the Network handler would never be called.
         */
        @CheckResult(suggest = "run()")
        fun attachErrorHandler(commandErrorHandler: ErrorHandler): CommandBuilder = this.apply {
            errorHandlers.add(commandErrorHandler)
        }
    }
}
