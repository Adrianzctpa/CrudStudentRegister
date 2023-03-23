package com.example.crudstudentregister.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDAO {
    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student_data_table")
    fun getStudents() : LiveData<List<Student>>


}