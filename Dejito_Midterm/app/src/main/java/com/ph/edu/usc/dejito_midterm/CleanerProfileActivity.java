package com.ph.edu.usc.dejito_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CleanerProfileActivity extends AppCompatActivity {

    private TextView cleanerName, cleanerAge, cleanerAddress, cleanerNumber, cleanerGender, cleanerServices, cleanerDetails, cleanerRating, cleanPts, attitudePts, satisfactionPts;
    private ProgressBar attitudeBar, cleaningBar, satisfactionBar;
    private ImageView cleanerImage;
    private Button findAnother, book;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_profile);

        cleanerName = findViewById(R.id.cleanerName);
        cleanerGender = findViewById(R.id.cleanerGender);
        cleanerServices = findViewById(R.id.cleanerServices);

        cleanerRating = findViewById(R.id.cleanerRating);
        cleanerAge = findViewById(R.id.cleanerAge);
        cleanerAddress = findViewById(R.id.cleanerAddress);
        cleanerNumber = findViewById(R.id.cleanerNumber);
        cleanerDetails = findViewById(R.id.cleanerAbout);
        cleanerImage = findViewById(R.id.cleanerImage);

        cleaningBar = findViewById(R.id.cleanBar);
        attitudeBar = findViewById(R.id.attitudeBar);
        satisfactionBar = findViewById(R.id.satisfactionBar);

        cleanPts = findViewById(R.id.cleanPts);
        attitudePts = findViewById(R.id. attitudePts);
        satisfactionPts = findViewById(R.id.satisfactionPts);


        String name = getIntent().getStringExtra("cleaner_name");
        String gender = getIntent().getStringExtra("cleaner_gender");
        ArrayList<String> services = getIntent().getStringArrayListExtra("cleaner_services");
        int age = getIntent().getIntExtra("cleaner_age", 0);
        float rating = getIntent().getFloatExtra("cleaner_rating", 0);
        String address = getIntent().getStringExtra("cleaner_address");
        String number = getIntent().getStringExtra("cleaner_number");
        String details = getIntent().getStringExtra("cleaner_details");
        int imageResource = getIntent().getIntExtra("cleaner_image", 0);

        int cleaningScore = getIntent().getIntExtra("cleaner_clean", 0);
        int attitudeScore = getIntent().getIntExtra("cleaner_attitude", 0);
        int satisfactionScore = getIntent().getIntExtra("cleaner_satisfaction", 0);

        // Populate Data
        cleanerName.setText(name);
        cleanerGender.setText(gender);
        cleanerAge.setText("Age: " + age);
        cleanerRating.setText("Rating: " + String.valueOf(rating));
        cleanerAddress.setText("Address: " + address);
        cleanerNumber.setText("Mobile: " + number);
        cleanerDetails.setText(details);
        cleanerImage.setImageResource(imageResource);

        cleanPts.setText(String.valueOf(cleaningScore));
        attitudePts.setText(String.valueOf(attitudeScore));
        satisfactionPts.setText(String.valueOf(satisfactionScore));

        if (services != null) {
            cleanerServices.setText(String.join(", ", services));
        }

        attitudeBar.setProgress(attitudeScore);
        cleaningBar.setProgress(cleaningScore);
        satisfactionBar.setProgress(satisfactionScore);

        // Back Button
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());

        // Find Another Cleaner
        findAnother = findViewById(R.id.findAnother);
        findAnother.setOnClickListener(v -> finish());

        // Booking Button
        book = findViewById(R.id.book);
        book.setOnClickListener(v -> {
            Intent intent = new Intent(CleanerProfileActivity.this, BookingActivity.class);
            intent.putExtra("cleaner_name", name);
            intent.putExtra("cleaner_gender", gender);
            intent.putExtra("cleaner_rating", rating);
            intent.putExtra("cleaner_age", age);
            intent.putExtra("cleaner_address", address);
            intent.putExtra("cleaner_number", number);
            intent.putExtra("cleaner_details", details);
            intent.putExtra("cleaner_image", imageResource);
            intent.putStringArrayListExtra("cleaner_services", services);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
