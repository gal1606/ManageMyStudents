package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.Model.Model
import com.example.studentsapp.Model.Student

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {

            val updatedIsChecked = findViewById<CheckBox>(R.id.checkBox).isChecked

            Model.instance.selectedStudent?.apply {
                isChecked = updatedIsChecked
            }

            val selectedStudent = Model.instance.selectedStudent
            selectedStudent?.let { student ->
                val index = Model.instance.students.indexOfFirst { it.id == student.id }
                if (index >= 0) {
                    Model.instance.students[index] = student
                }
            }

            finish()
        }


        val editStudentButton: Button = findViewById(R.id.btnEdit)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")
        val isChecked = intent.getBooleanExtra("isChecked", false)

        findViewById<TextView>(R.id.tvNameData).text = name
        findViewById<TextView>(R.id.tvIdData).text = id
        findViewById<TextView>(R.id.tvPhoneData).text = phone
        findViewById<TextView>(R.id.tvAddressData).text = address
        findViewById<CheckBox>(R.id.checkBox).isChecked = isChecked

        val student = Student(
            name = name ?: "",
            id = id ?: "",
            phone = phone ?: "",
            address = address ?: "",
            avatar = "res/drawable/avatar.png",
            isChecked = isChecked
        )
        Model.instance.selectedStudent = student

        editStudentButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            startActivity(intent)
        }
    }
}
