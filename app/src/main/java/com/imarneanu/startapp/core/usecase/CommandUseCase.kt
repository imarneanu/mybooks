package com.imarneanu.startapp.core.usecase

interface CommandUseCase {
    suspend operator fun invoke()
}

interface CommandUseCase1<T1> {
    suspend operator fun invoke(t1: T1)
}

interface CommandUseCase2<T1, T2> {
    suspend operator fun invoke(t1: T1, t2: T2)
}

interface CommandUseCase3<T1, T2, T3> {
    suspend operator fun invoke(t1: T1, t2: T2, t3: T3)
}

interface CommandUseCase4<T1, T2, T3, T4> {
    suspend operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4)
}

interface CommandUseCase5<T1, T2, T3, T4, T5> {
    suspend operator fun invoke(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5)
}
