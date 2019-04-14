package ru.gruzdev.livedatadaggerexample

import android.app.Application
import ru.gruzdev.livedatadaggerexample.di.ApplicationComponent
import ru.gruzdev.livedatadaggerexample.di.DaggerApplicationComponent
import ru.gruzdev.livedatadaggerexample.di.modules.ApplicationModule
import ru.gruzdev.livedatadaggerexample.di.modules.PersonModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        makeComponent()
    }

    fun makeComponent() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .personModule(PersonModule())
            .build()
    }

    companion object {
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent
    }
}