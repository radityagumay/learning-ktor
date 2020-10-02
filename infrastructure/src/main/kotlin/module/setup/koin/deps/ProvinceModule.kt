package main.kotlin.module.setup.koin.deps

import org.koin.core.module.Module
import org.koin.dsl.module
import service.login.controller.ProvinceController
import service.login.data.ProvinceRepository
import service.login.data.impl.ProvinceRepositoryImpl
import service.login.domain.ProvinceUseCase
import service.login.domain.impl.ProvinceUseCaseImpl

val provinceKoinModule: Module
    get() = module {
        // Controllers
        single { ProvinceController(get()) }

        // Services
        single<ProvinceUseCase> { ProvinceUseCaseImpl(get()) }

        // Repositories
        single<ProvinceRepository> { ProvinceRepositoryImpl() }
    }
