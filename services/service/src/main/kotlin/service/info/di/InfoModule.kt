package service.info.di

import memory.cache.CACHE_AUTH_DATA
import memory.cache.MemoryCache
import org.koin.core.module.Module
import org.koin.dsl.module
import service.info.controller.InfoController
import service.info.data.InfoRepository
import service.info.data.impl.InfoRepositoryImpl
import service.info.domain.InfoUseCase
import service.info.domain.impl.InfoUseCaseImpl

val infoKoinModule: Module
    get() = module {
        // Controllers
        single { InfoController(get()) }

        // Services
        single<InfoUseCase> { InfoUseCaseImpl(get()) }

        // Repositories
        single<InfoRepository> { InfoRepositoryImpl(MemoryCache.instance(CACHE_AUTH_DATA)) }
    }
