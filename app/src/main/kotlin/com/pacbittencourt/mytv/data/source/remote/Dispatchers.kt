package com.pacbittencourt.mytv.data.source.remote

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: MyTvDispatchers)

enum class MyTvDispatchers {
    Default,
    IO,
}