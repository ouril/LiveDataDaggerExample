package ru.gruzdev.livedatadaggerexample.models

data class Person(var name: String = "", var phoneNumber: String = "", var email: String = "") {
    fun fullStringData(): String = "$name\n$phoneNumber\n$email"
}