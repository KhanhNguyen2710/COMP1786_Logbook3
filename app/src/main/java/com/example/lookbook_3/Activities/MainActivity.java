package com.example.lookbook_3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.lookbook_3.Database.AppDatabase;
import com.example.lookbook_3.Models.Person;
import com.example.lookbook_3.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "sqlite_example_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();




        Button saveDetailsButton = findViewById(R.id.saveDetailsButton);
        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });


//
        Button viewDetailsButton = findViewById(R.id.viewDetailsButton);
        viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveDetails() {


        // Get references to the EditText views and read their content
        EditText nameTxt = findViewById(R.id.nameText);
        EditText dobTxt = findViewById(R.id.dobText);
        EditText emailTxt = findViewById(R.id.emailText);


        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        RadioGroup imageSelectionGroup = findViewById(R.id.imageSelectionGroup);
        int selectedRadioButtonId = imageSelectionGroup.getCheckedRadioButtonId();
        boolean imageSelection = (selectedRadioButtonId == R.id.radioImage1);



        Person person = new Person();
        person.name = name;
        person.dob = dob;
        person.email = email;
        person.imageSelection= imageSelection;


        // Calls the insertDetails method we created
        long personId = appDatabase.personDao().insertPerson(person);

//       long personId = dbHelper.insertDetails(name, dob, email,imagePath);

        // Shows a toast with the automatically generated id
        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();

        // Launch Details Activity
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}