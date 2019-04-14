package ru.gruzdev.livedatadaggerexample.di.modules


import android.app.Application

import dagger.Module
import dagger.Provides
import ru.gruzdev.livedatadaggerexample.models.Person
import javax.inject.Singleton


@Module
class PersonModule {
    @Provides
    @Singleton
    fun providePerson(): Person = Person()
}