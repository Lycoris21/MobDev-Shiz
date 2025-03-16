package com.ph.edu.usc.dejito_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategories;
    private RecyclerView recyclerViewTopCleaners;
    private EditText searchEditText;
    private CategoryAdapter categoryAdapter;
    private CleanerAdapter cleanerAdapter;

    private List<Cleaner> allCleaners; // List of all cleaners
    private List<Cleaner> filteredCleaners; // List of filtered cleaners

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerViewCategories = findViewById(R.id.categories);
        recyclerViewTopCleaners = findViewById(R.id.topCleaners);
        searchEditText = findViewById(R.id.search);
        LinearLayout moreCategories = findViewById(R.id.more);

        // Initialize the list of cleaners
        initializeCleaners();

        // Set up RecyclerView for categories
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Cleaning Service", "Professional cleaning services for your home.", R.drawable.cleaning));
        categoryList.add(new Category("Cleaning Appliance", "Services for cleaning appliances.", R.drawable.appliance));
        categoryList.add(new Category("Babysitter", "Trusted babysitting services for your children.", R.drawable.babysitter));

        categoryAdapter = new CategoryAdapter(categoryList, category -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectionActivity.class);
            intent.putExtra("category", category.getName()); // Pass the category name
            startActivity(intent);
        });
        recyclerViewCategories.setAdapter(categoryAdapter);

        // Set up RecyclerView for top cleaners
        recyclerViewTopCleaners.setLayoutManager(new LinearLayoutManager(this));
        cleanerAdapter = new CleanerAdapter(allCleaners, cleaner -> {
            Intent intent = new Intent(MainActivity.this, CleanerProfileActivity.class);
            // Pass the cleaner's details to the CleanerProfileActivity
            intent.putExtra("cleaner_name", cleaner.getName());
            intent.putExtra("cleaner_gender", cleaner.getGender());
            intent.putExtra("cleaner_details", cleaner.getDetails());
            intent.putExtra("cleaner_rating", cleaner.getRating());
            intent.putExtra("cleaner_image", cleaner.getImageResource());
            intent.putStringArrayListExtra("cleaner_services", new ArrayList<>(cleaner.getCategory())); // Pass the services
            startActivity(intent);
        });
        recyclerViewTopCleaners.setAdapter(cleanerAdapter);

        // Set up search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCleaners(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Handle "More" button click
        moreCategories.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectionActivity.class);
            startActivity(intent);
        });
    }

    private void initializeCleaners() {
        allCleaners = new ArrayList<>();

        List<String> services1 = new ArrayList<>();
        services1.add("Cleaning Service");
        services1.add("Cleaning Appliance");

        List<String> services2 = new ArrayList<>();
        services2.add("Cleaning Service");
        services2.add("Cleaning Appliance");
        services2.add("Babysitter");

        List<String> services3 = new ArrayList<>();
        services3.add("Cleaning Service");
        services3.add("Babysitter");

        List<String> services4 = new ArrayList<>();
        services4.add("Babysitter");

        allCleaners.add(new Cleaner("Cleaner 1", "Female", "Experienced in house cleaning", 4.5f, R.drawable.jingliu, services1));
        allCleaners.add(new Cleaner("Cleaner 2", "Male", "Specializes in garden maintenance", 4.0f, R.drawable.jingliu, services2));
        allCleaners.add(new Cleaner("Cleaner 3", "Female", "Expert plumber with 10 years of experience", 5.0f, R.drawable.jingliu, services3));
        allCleaners.add(new Cleaner("Cleaner 4", "Male", "Certified babysitter", 4.8f, R.drawable.jingliu, services4));
        // Add more cleaners as needed
    }

    private void filterCleaners(String query) {
        filteredCleaners = new ArrayList<>();
        for (Cleaner cleaner : allCleaners) {
            if (cleaner.getName().toLowerCase().contains(query.toLowerCase()) ||
                    cleaner.getDetails().toLowerCase().contains(query.toLowerCase())) {
                filteredCleaners.add(cleaner);
            }
        }
        cleanerAdapter.updateList(filteredCleaners);
    }
}