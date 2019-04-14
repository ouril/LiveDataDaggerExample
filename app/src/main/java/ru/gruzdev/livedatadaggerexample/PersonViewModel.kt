package ru.gruzdev.livedatadaggerexample

import androidx.lifecycle.*
import ru.gruzdev.livedatadaggerexample.models.Person
import javax.inject.Inject

class PersonViewModel : ViewModel() {
    init {
        App.applicationComponent.inject(this)
    }

    @Inject
    lateinit var person: Person

    val liveData = MutableLiveData<Person>()

    fun observerData(owner: LifecycleOwner, observer: Observer<Person>) {
        liveData.observe(owner, observer)
    }

    fun savePerson(newPerson: Person) {
        person = newPerson
        liveData.value = person
    }
}