package com.example.lookbook_3.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lookbook_3.Models.Person;
import com.example.lookbook_3.adapters.ContactAdapter;
import com.example.lookbook_3.Database.AppDatabase;
import com.example.lookbook_3.R;


import java.util.List;

public class DetailsActivity extends AppCompatActivity implements ContactAdapter.OnDeleteClickListener {
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    List<Person> persons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        persons = appDatabase.personDao().getAllPersons();

        adapter = new ContactAdapter(persons, this);
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onDeleteClick(Person person) {
        new AlertDialog.Builder(this)
                .setTitle("Delete ")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Remove from the database
                    appDatabase.personDao().deletePerson(person);
                    // Update the list after deletion
                    persons.remove(person);
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}