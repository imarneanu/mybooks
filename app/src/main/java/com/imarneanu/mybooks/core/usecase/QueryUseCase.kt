package com.imarneanu.mybooks.core.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCase<R> {
    operator fun invoke(): Flow<R>
}

interface QueryUseCase1<T1, R> {
    operator fun invoke(t1: T1): Flow<R>
}

interface QueryUseCase2<T1, T2, R> {
    operator fun invoke(t1: T1, t2: T2): Flow<R>
}

interface QueryUseCase3<T1, T2, T3, R> {
    operator fun invoke(t1: T1, t2: T2, t3: T3): Flow<R>
}

interface QueryUseCase4<T1, T2, T3, T4, R> {
    operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4): Flow<R>
}

interface QueryUseCase5<T1, T2, T3, T4, T5, R> {
    operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5): Flow<R>
}
