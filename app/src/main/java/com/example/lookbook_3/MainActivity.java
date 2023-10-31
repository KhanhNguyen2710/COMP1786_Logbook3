package com.example.lookbook_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Button saveDetailsButton = findViewById(R.id.saveDetailsButton);

        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });



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
        // Creates an object of our helper class
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Get references to the EditText views and read their content
        EditText nameTxt = findViewById(R.id.nameText);
        EditText dobTxt = findViewById(R.id.dobText);
        EditText emailTxt = findViewById(R.id.emailText);



        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();



        String imagePath = "";  // Thay đổi đây để lưu đường dẫn hoặc tên tệp ảnh

        // Determine which RadioButton is selected and set imagePath accordingly
        RadioGroup imageSelectionGroup = findViewById(R.id.imageSelectionGroup);
        int selectedRadioButtonId = imageSelectionGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.radioImage1) {
            imagePath = "pic1.png";  // Đường dẫn hoặc tên tệp ảnh cho radioImage1
        } else if (selectedRadioButtonId == R.id.radioImage2) {
            imagePath = "pic2.png";  // Đường dẫn hoặc tên tệp ảnh cho radioImage2
        }


        // Calls the insertDetails method we created
        long personId = dbHelper.insertDetails(name, dob, email,imagePath);

        // Shows a toast with the automatically generated id
        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();

        // Launch Details Activity
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}