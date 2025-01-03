package dev.lkeeeey.edu.you.di

import dev.lkeeeey.edu.you.auth.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.you.auth.splash.viewmodel.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
}
