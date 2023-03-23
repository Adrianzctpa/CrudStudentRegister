package com.example.crudstudentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudstudentregister.databinding.ActivityMainBinding
import com.example.crudstudentregister.db.Student
import com.example.crudstudentregister.db.StudentDB

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel : StudentViewModel
    private lateinit var adapter: StudentRecyclerViewAdapter
    private var isItemClicked = false

    private var selectedStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val dao = StudentDB.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory)[StudentViewModel::class.java]

        binding.apply {
            btnSave.setOnClickListener {
                if (isItemClicked) {
                    updateStudentData()
                } else {
                    saveStudentData()
                }

                clearInput()
            }

            btnClear.setOnClickListener {
                if (isItemClicked) {
                    deleteStudentData()
                }

                clearInput()
            }
        }

        initRecyclerView()
    }

    private fun saveStudentData() {
        binding.apply {
            viewModel.insertStudent(
                Student(
                    0,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
        }
    }

    private fun updateStudentData() {
        binding.apply {
            viewModel.updateStudent(
                Student(
                    selectedStudent!!.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
        }

        selectedStudent = null
        isItemClicked = false
        setBtnMode()
    }

    private fun deleteStudentData() {
        binding.apply {
            viewModel.deleteStudent(
                Student(
                    selectedStudent!!.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
        }

        selectedStudent = null
        isItemClicked = false
        setBtnMode()
    }

    private fun clearInput() {
        binding.apply {
            etName.setText("")
            etEmail.setText("")
        }
    }

    private fun initRecyclerView() {
        binding.rvStudent.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter {
            selectedItem: Student -> clickListItem(selectedItem)
        }
        binding.rvStudent.adapter = adapter

        displayStudentsList()
    }

    private fun displayStudentsList() {
        viewModel.students.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun clickListItem(student: Student) {
        selectedStudent = student
        isItemClicked = true
        setBtnMode()

        binding.apply {
            etName.setText(selectedStudent!!.name)
            etEmail.setText(selectedStudent!!.email)
        }
    }

    private fun setBtnMode() {
        binding.apply {
            if (isItemClicked) {
                btnSave.text = "Update"
                btnClear.text = "Delete"
            } else {
                btnSave.text = "Save"
                btnClear.text = "Clear"
            }
        }
    }
}