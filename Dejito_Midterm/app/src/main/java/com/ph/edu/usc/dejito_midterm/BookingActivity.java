package com.ph.edu.usc.dejito_midterm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private TextView cleanerName;
    private TextView cleanerGender;
    private TextView cleanerCategory;
    private TextView cleanerDetails;
    private TextView cleanerRating;
    private ImageView cleanerImage;

    private EditText serviceDuration;

    private int selectedYear, selectedMonth, selectedDay;

    ImageView back;

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        cleanerName = findViewById(R.id.cleanerName);
        cleanerGender = findViewById(R.id.cleanerGender);
        cleanerCategory = findViewById(R.id.cleanerServiceType);
        cleanerImage = findViewById(R.id.cleanerImage);
        Spinner serviceItemsSpinner = findViewById(R.id.serviceItems);

        String name = getIntent().getStringExtra("cleaner_name");
        String gender = getIntent().getStringExtra("cleaner_gender");
        String category = getIntent().getStringExtra("cleaner_category");
        int imageResource = getIntent().getIntExtra("cleaner_image", 0);
        ArrayList<String> services = getIntent().getStringArrayListExtra("cleaner_services");

        serviceDuration = findViewById(R.id.serviceDuration);

        cleanerName.setText(name);
        cleanerGender.setText(gender);
        cleanerCategory.setText(category);
        cleanerImage.setImageResource(imageResource);

        if (services != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, services);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            serviceItemsSpinner.setAdapter(adapter);
        }

        serviceDuration.setOnClickListener(v -> openDatePicker());

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(BookingActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void openDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedYear = year;
                    selectedMonth = month;
                    selectedDay = dayOfMonth;
                    serviceDuration.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                },
                selectedYear, selectedMonth, selectedDay
        );
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}