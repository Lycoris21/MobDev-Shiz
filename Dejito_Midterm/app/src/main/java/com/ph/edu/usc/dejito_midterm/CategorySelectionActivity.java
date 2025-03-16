package com.ph.edu.usc.dejito_midterm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CategorySelectionActivity extends AppCompatActivity {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(v -> onBackPressed());

        // Load the CategorySelectionFragment by default
        if (savedInstanceState == null) {
            String category = getIntent().getStringExtra("category"); // Retrieve the category from the intent
            CategorySelectionFragment categorySelectionFragment = new CategorySelectionFragment();
            Bundle args = new Bundle();
            args.putString("category", category); // Pass the category to the fragment
            categorySelectionFragment.setArguments(args);
            loadFragment(categorySelectionFragment);
        }
    }
    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null); // Add to back stack
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Pop the last fragment
        } else {
            // If there are no fragments in the back stack, finish the activity
            super.onBackPressed();
        }
    }
}