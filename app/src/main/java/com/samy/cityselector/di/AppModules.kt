package com.samy.cityselector.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samy.cityselector.data.CityRepositoryImpl
import com.samy.cityselector.data.localdata.CityRawLocalDataSource
import com.samy.cityselector.data.localdata.CityRawLocalDataSourceImpl
import com.samy.cityselector.data.mapper.CityDomainMapper
import com.samy.cityselector.domain.CityRepository
import com.samy.cityselector.domain.mapper.CityViewEntityMapper
import com.samy.cityselector.domain.usecase.GetCitesList
import org.koin.dsl.module


val dataModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }
    single { provideGson() }

    single<CityRawLocalDataSource> { CityRawLocalDataSourceImpl(get(), get()) }

    factory { CityDomainMapper() }

    factory<CityRepository> { CityRepositoryImpl(get(), get()) }
}
val domainModule = module {
    factory { CityViewEntityMapper() }
    factory { GetCitesList(get(), get()) }
}
val viewModelModule = module {}

