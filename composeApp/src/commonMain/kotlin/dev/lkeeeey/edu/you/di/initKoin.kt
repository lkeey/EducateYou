package dev.lkeeeey.edu.you.di

import dev.lkeeeey.edu.you.di.platformModule
import dev.lkeeeey.edu.you.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}
