package com.tnl.lab07_ex2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM STUDENT WHERE ID = :id")
    Student get(long id);

    @Query("SELECT * FROM STUDENT")
    List<Student> getAll();
}
