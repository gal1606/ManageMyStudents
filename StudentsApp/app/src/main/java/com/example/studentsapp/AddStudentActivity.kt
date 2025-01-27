package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.Model.Model
import com.example.studentsapp.Model.Student

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        val saveButton: Button = findViewById(R.id.btnSave)
        val cancelButton: Button = findViewById(R.id.btnCancel)

        saveButton.setOnClickListener {
            saveStudent()
        }

        cancelButton.setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

    }

    private fun saveStudent() {

        val nameField: EditText = findViewById(R.id.editTextName)
        val idField: EditText = findViewById(R.id.editTextId)
        val phoneField: EditText = findViewById(R.id.editTextPhone)
        val addressField: EditText = findViewById(R.id.editTextAddress)
        val isCheckedBox: CheckBox = findViewById(R.id.checkBox)

        val name = nameField.text.toString().trim()
        val id = idField.text.toString().trim()
        val phone = phoneField.text.toString().trim()
        val address = addressField.text.toString().trim()
        val isChecked = isCheckedBox.isChecked

        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val newStudent = Student(
            name = name,
            id = id,
            phone = phone,
            address = address,
            avatar = "res/drawable/avatar.png",
            isChecked = isChecked
        )

        Model.instance.students.add(newStudent)

        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
