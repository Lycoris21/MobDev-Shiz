package com.ph.edu.usc.dejito_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CleanerProfileActivity extends AppCompatActivity {

    private TextView cleanerName;
    private TextView cleanerGender;
    private TextView cleanerCategory;
    private TextView cleanerDetails;
    private TextView cleanerRating;
    private TextView cleanerServices; // New TextView for services
    private ImageView cleanerImage;

    ImageView back;
    Button findAnother;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_profile);

        // Initialize views
        cleanerName = findViewById(R.id.cleanerName);
        cleanerGender = findViewById(R.id.cleanerGender);
        cleanerDetails = findViewById(R.id.cleanerAbout);
        cleanerRating = findViewById(R.id.cleanerRating);
        cleanerImage = findViewById(R.id.cleanerImage);
        cleanerServices = findViewById(R.id.cleanerServices); // New TextView for services

        // Get the intent extras
        String name = getIntent().getStringExtra("cleaner_name");
        String gender = getIntent().getStringExtra("cleaner_gender");
        String details = getIntent().getStringExtra("cleaner_details");
        float rating = getIntent().getFloatExtra("cleaner_rating", 0);
        int imageResource = getIntent().getIntExtra("cleaner_image", 0);
        ArrayList<String> services = getIntent().getStringArrayListExtra("cleaner_services"); // Get the services

        // Set the data to the views
        cleanerName.setText(name);
        cleanerGender.setText(gender);
        cleanerDetails.setText(details);
        cleanerRating.setText(String.valueOf(rating));
        cleanerImage.setImageResource(imageResource);

        // Display the services
        if (services != null) {
            cleanerServices.setText(String.join(", ", services)); // Join services into a single string
        }

        // Initialize buttons
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());

        findAnother = findViewById(R.id.findAnother);
        findAnother.setOnClickListener(v -> finish());

        book = findViewById(R.id.book);
        book.setOnClickListener(v -> {
            Intent intent = new Intent(CleanerProfileActivity.this, BookingActivity.class);
            // Pass the cleaner's details to the BookingActivity
            intent.putExtra("cleaner_name", name);
            intent.putExtra("cleaner_gender", gender);
            intent.putExtra("cleaner_details", details);
            intent.putExtra("cleaner_rating", rating);
            intent.putExtra("cleaner_image", imageResource);
            intent.putStringArrayListExtra("cleaner_services", services); // Pass the services
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Call the super method to handle the back press
    }
}