package ru.gruzdev.livedatadaggerexample.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = application
}