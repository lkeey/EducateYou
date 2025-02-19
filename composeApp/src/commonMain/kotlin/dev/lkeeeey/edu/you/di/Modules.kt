package dev.lkeeeey.edu.you.di

import dev.lkeeeey.edu.core.data.HttpClientFactory
import dev.lkeeeey.edu.you.auth.data.network.AuthDataSource
import dev.lkeeeey.edu.you.auth.data.network.RemoteAuthDataSource
import dev.lkeeeey.edu.you.auth.data.repository.AuthRepositoryImpl
import dev.lkeeeey.edu.you.auth.domain.AuthRepository
import dev.lkeeeey.edu.you.auth.presentation.login.viewmodel.LoginViewModel
import dev.lkeeeey.edu.you.auth.presentation.register.viewmodel.RegisterViewModel
import dev.lkeeeey.edu.you.auth.presentation.splash.viewmodel.SplashViewModel
import dev.lkeeeey.edu.you.main.viewmodel.CalendarViewModel
import dev.lkeeeey.edu.you.profile.data.network.ProfileDataSource
import dev.lkeeeey.edu.you.profile.data.network.ProfileDataSourceImpl
import dev.lkeeeey.edu.you.profile.data.repository.ProfileRepositoryImpl
import dev.lkeeeey.edu.you.profile.domain.ProfileRepository
import dev.lkeeeey.edu.you.profile.presentation.students.viewmodel.StudentsViewModel
import dev.lkeeeey.edu.you.profile.presentation.tabs.viewmodel.ProfileViewModel
import dev.lkeeeey.edu.you.profile.presentation.tasks.viewmodel.TasksViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::CalendarViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::StudentsViewModel)
    viewModelOf(::TasksViewModel)

    single { HttpClientFactory.create(get()) }

    singleOf(::AuthDataSource).bind<RemoteAuthDataSource>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()

    singleOf(::ProfileDataSourceImpl).bind<ProfileDataSource>()
    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()
}
