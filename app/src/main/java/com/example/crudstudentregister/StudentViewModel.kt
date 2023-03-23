package com.example.crudstudentregister

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudstudentregister.db.Student
import com.example.crudstudentregister.db.StudentDAO
import com.example.crudstudentregister.db.StudentDB
import kotlinx.coroutines.launch

class StudentViewModel(private val dao: StudentDAO) : ViewModel() {
    val students = dao.getStudents()

    fun insertStudent(student: Student) = viewModelScope.launch {
        dao.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        dao.updateStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        dao.deleteStudent(student)
    }
}