package com.example.studentsapp

import android.content.Intent
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

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)

        val saveButton: Button = findViewById(R.id.btnSave)
        val cancelButton: Button = findViewById(R.id.btnCancel)
        val deleteButton: Button = findViewById(R.id.btnDelete)

        val student = Model.instance.selectedStudent

        if (student != null) {
            findViewById<EditText>(R.id.editTextName).setText(student.name)
            findViewById<EditText>(R.id.editTextId).setText(student.id)
            findViewById<EditText>(R.id.editTextPhone).setText(student.phone)
            findViewById<EditText>(R.id.editTextAddress).setText(student.address)
            findViewById<CheckBox>(R.id.checkBox).isChecked = student.isChecked
        }

        saveButton.setOnClickListener {
            saveStudent()
        }

        cancelButton.setOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener{
            deleteStudent()
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun deleteStudent() {
        val selectedStudent = Model.instance.selectedStudent

        if (selectedStudent != null) {
            val studentList = Model.instance.students
            val index = studentList.indexOfFirst { it.id == selectedStudent.id }
            if (index != -1) {
                studentList.removeAt(index)
                Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Student not found in the list", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No student selected to delete", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, StudentsListActivity::class.java)
        startActivity(intent)
        finish()
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

        val selectedStudent = Model.instance.selectedStudent

        if (selectedStudent != null) {
            val studentList = Model.instance.students
            val index = studentList.indexOfFirst { it.id == selectedStudent.id }
            if (index != -1) {
                studentList[index] = Student(
                    name = name,
                    id = id,
                    phone = phone,
                    address = address,
                    avatar = selectedStudent.avatar,
                    isChecked = isChecked
                )
            }
        } else {
            Toast.makeText(this, "No student selected to update", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, StudentsListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
