package com.example.studentsapp.Model

data class Student(val name: String,
                   val id: String,
                   val phone: String,
                   val address: String,
                   val avatar: String,
                   var isChecked: Boolean)
