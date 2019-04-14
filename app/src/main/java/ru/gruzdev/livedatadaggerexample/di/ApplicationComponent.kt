package ru.gruzdev.livedatadaggerexample.di

import android.app.Application
import android.app.Person
import dagger.Component
import ru.gruzdev.livedatadaggerexample.PersonViewModel
import ru.gruzdev.livedatadaggerexample.di.modules.ApplicationModule
import ru.gruzdev.livedatadaggerexample.di.modules.PersonModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, PersonModule::class])
interface ApplicationComponent {
    fun application(): Application

    fun inject(viewModel: PersonViewModel)


}
