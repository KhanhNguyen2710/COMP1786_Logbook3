package com.example.lookbook_3.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lookbook_3.Models.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    long insertPerson(Person person);


    @Query("SELECT * FROM details ORDER BY name")
    List<Person> getAllPersons();


    @Delete
    void deletePerson(Person person);
}