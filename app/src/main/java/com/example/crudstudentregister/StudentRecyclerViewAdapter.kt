package com.example.crudstudentregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudstudentregister.db.Student

class StudentRecyclerViewAdapter(private val clickListener: (Student) -> Unit): RecyclerView.Adapter<StudentViewHolder>() {
    private val studentList = ArrayList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return StudentViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setList(students: List<Student>) {
        studentList.clear()
        studentList.addAll(students)
    }
}

class StudentViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bind(student: Student, clickListener: (Student) -> Unit) {
        val nameView = view.findViewById<TextView>(R.id.tvName)
        val emailView = view.findViewById<TextView>(R.id.tvEmail)
        nameView.text = student.name
        emailView.text = student.email
        view.setOnClickListener {
            clickListener(student)
        }
    }
}