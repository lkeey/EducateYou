package dev.lkeeeey.edu.you.di

import dev.lkeeeey.edu.you.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.you.auth.presentation.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.you.auth.presentation.splash.viewmodel.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}
