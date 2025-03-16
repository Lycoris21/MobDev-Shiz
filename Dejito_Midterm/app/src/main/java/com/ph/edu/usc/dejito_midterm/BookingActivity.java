package com.ph.edu.usc.dejito_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {

    private TextView cleanerName;
    private TextView cleanerGender;
    private TextView cleanerCategory;
    private TextView cleanerDetails;
    private TextView cleanerRating;
    private ImageView cleanerImage;

    ImageView back;

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize views
        cleanerName = findViewById(R.id.cleanerName);
        cleanerGender = findViewById(R.id.cleanerGender);
        cleanerCategory = findViewById(R.id.cleanerServiceType);
        cleanerImage = findViewById(R.id.cleanerImage);
        Spinner serviceItemsSpinner = findViewById(R.id.serviceItems); // Initialize the Spinner

        // Get the intent extras
        String name = getIntent().getStringExtra("cleaner_name");
        String gender = getIntent().getStringExtra("cleaner_gender");
        String category = getIntent().getStringExtra("cleaner_category");
        int imageResource = getIntent().getIntExtra("cleaner_image", 0); // Handle image resource
        ArrayList<String> services = getIntent().getStringArrayListExtra("cleaner_services"); // Get the services

        // Set the data to the views
        cleanerName.setText(name);
        cleanerGender.setText(gender);
        cleanerCategory.setText(category);
        cleanerImage.setImageResource(imageResource);

        // Populate the Spinner with services
        if (services != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, services);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            serviceItemsSpinner.setAdapter(adapter);
        }

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(BookingActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Call the super method to handle the back press
    }
}