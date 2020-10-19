package service.login.di

import memory.cache.CACHE_USER_DATA
import memory.cache.CACHE_AUTH_DATA
import memory.cache.MemoryCache
import org.koin.core.module.Module
import org.koin.dsl.module
import service.login.controller.LoginController
import service.login.data.LoginRepository
import service.login.data.impl.LoginRepositoryImpl
import service.login.domain.LoginUseCase
import service.login.domain.impl.LoginUseCaseImpl

val loginKoinModule: Module
    get() = module {
        // Controllers
        single { LoginController(get()) }

        // Services
        single<LoginUseCase> { LoginUseCaseImpl(get()) }

        // Repositories
        single<LoginRepository> {
            LoginRepositoryImpl(
                MemoryCache.instance(CACHE_USER_DATA),
                MemoryCache.instance(CACHE_AUTH_DATA)
            )
        }
    }
