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

        if (savedInstanceState == null) {
            String category = getIntent().getStringExtra("category");
            CategorySelectionFragment categorySelectionFragment = new CategorySelectionFragment();
            Bundle args = new Bundle();
            args.putString("category", category);
            categorySelectionFragment.setArguments(args);
            loadFragment(categorySelectionFragment);
        }
    }
    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}