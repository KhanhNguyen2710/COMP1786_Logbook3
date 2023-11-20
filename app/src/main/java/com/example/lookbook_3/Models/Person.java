package com.example.lookbook_3.Models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "details")
public class Person {
    @PrimaryKey(autoGenerate = true)
    public long person_id;
    public String name;
    public String dob;
    public String email;

    public boolean imageSelection;

//    public String image;
}