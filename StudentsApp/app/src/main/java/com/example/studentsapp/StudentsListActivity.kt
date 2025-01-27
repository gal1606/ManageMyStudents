package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.Model.Model
import com.example.studentsapp.Model.Student

class StudentsListActivity : AppCompatActivity() {

    var studentRecyclerView: RecyclerView? = null
    var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list)

        students = Model.instance.students

        studentRecyclerView = findViewById(R.id.rvStudentList)
        studentRecyclerView?.setHasFixedSize(true)
        studentRecyclerView?.layoutManager = LinearLayoutManager(this)

        val adapter = StudentRecyclerAdapter()
        adapter.listener = object : OnRowClickListener{

            override fun onStudentClicked(student: Student?) {
                if (student != null) {
                    val intent = Intent(this@StudentsListActivity, StudentDetailsActivity::class.java)
                    intent.putExtra("name", student.name)
                    intent.putExtra("id", student.id)
                    intent.putExtra("phone", student.phone)
                    intent.putExtra("address", student.address)
                    intent.putExtra("isChecked", student.isChecked)
                    startActivity(intent)
                }
            }
        }
        studentRecyclerView?.adapter = adapter

        val addStudentButton: Button = findViewById(R.id.btnAdd)
        fun onAddStudentButtonClicked(view: View?) {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
        addStudentButton.setOnClickListener(::onAddStudentButtonClicked)
    }

    override fun onResume() {
        super.onResume()
        studentRecyclerView?.adapter?.notifyDataSetChanged()
    }


    interface OnRowClickListener {

        fun onStudentClicked(student: Student?)
    }

    inner class StudentViewHolder(val itemView: View, val listener: OnRowClickListener?): RecyclerView.ViewHolder(itemView){

        var nameTextView: TextView? = null
        var idTextView: TextView? = null
        var studentCheckBox: CheckBox? = null
        var student: Student? = null
        init {
            nameTextView= itemView. findViewById(R.id.tvSudentName)
            idTextView = itemView. findViewById(R.id.tvStudentId)
            studentCheckBox = itemView. findViewById(R.id.cbStudent)

            studentCheckBox?.setOnClickListener{
                val student = students?.get(adapterPosition)
                student?.isChecked = studentCheckBox?.isChecked ?: false
            }

            itemView.setOnClickListener{
                Log.i("Tag","StudentViewHolder: Position clicked $adapterPosition")
                listener?.onStudentClicked(student)
            }
        }

        fun bind(student: Student?) {
            this.student = student
            nameTextView?.text = student?.name
            idTextView?.text = student?.id
            studentCheckBox?.apply {
                isChecked = student?.isChecked ?: false

            }
        }
    }

    inner class StudentRecyclerAdapter: RecyclerView.Adapter<StudentViewHolder>(){

        var listener: OnRowClickListener? = null

        override fun getItemCount(): Int = students?.size ?: 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {

            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_layout_row, parent, false)
            return StudentViewHolder(itemView, listener)

        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {

            val student = students?.get(position)
            holder.bind(student, )
        }

    }
}