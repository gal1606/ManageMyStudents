package com.example.studentsapp.Model

import kotlin.random.Random

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()
    var selectedStudent: Student? = null

    companion object {
        val instance: Model = Model()
    }

    init {

        val names = listOf(
            "Antonio Garcia", "Luca Rossi", "Sophia Smith", "Emily Johnson", "John Brown", "Michael Davis",
            "Emma Wilson", "Olivia Martinez", "Lucas Taylor", "James Anderson", "Isabella Thomas",
            "Mia Moore", "Alexander Jackson", "Charlotte White", "Benjamin Harris", "Amelia Clark",
            "William Lewis", "Sophia Walker", "David Young", "Eva Hall"
        )

        for (i in 0 until names.size) {
            val student = Student(
                names[i],
                "Id: 00000$i",
                "+9725555$i",
                "Rothschild $i street, Tel Aviv",
                "res/drawable/avatar.png",
                false
            )
            students.add(student)
        }
    }

    fun updateSelectedStudent(student: Student) {
        selectedStudent = student
    }
}
