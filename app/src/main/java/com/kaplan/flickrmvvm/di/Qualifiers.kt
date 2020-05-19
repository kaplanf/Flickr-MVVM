package com.kaplan.flickrmvvm.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeIO

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeMain
