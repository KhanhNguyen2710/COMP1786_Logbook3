package com.example.lookbook_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Also declares an object of our helper class
        DatabaseHelper db = new DatabaseHelper(this);

        // Call the getDetails method we created
        String details = db.getDetails();
        TextView detailsTxt = findViewById(R.id.detailsText);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.pic1);

        // The text returned is just displayed
        detailsTxt.setText(details);



    }
}