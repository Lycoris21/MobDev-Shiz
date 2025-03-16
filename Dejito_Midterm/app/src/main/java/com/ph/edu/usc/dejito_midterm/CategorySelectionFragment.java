package com.ph.edu.usc.dejito_midterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategorySelectionFragment extends Fragment {

    private List<Category> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_selection, container, false);

        // Initialize categories
        categories = new ArrayList<>();
        categories.add(new Category("Cleaning Service", "Professional cleaning services for your home.", R.drawable.cleaning));
        categories.add(new Category("Cleaning Appliance", "Services for cleaning appliances.", R.drawable.appliance));
        categories.add(new Category("Babysitter", "Trusted babysitting services for your children.", R.drawable.babysitter));

        // Set up RecyclerView for categories
        RecyclerView recyclerView = view.findViewById(R.id.allCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        CategoryAdapter adapter = new CategoryAdapter(categories, category -> {
            // Handle category click
            CleanerListFragment cleanerListFragment = new CleanerListFragment();
            Bundle args = new Bundle();
            args.putString("category", category.getName()); // Ensure you're passing the correct category name
            cleanerListFragment.setArguments(args);
            ((CategorySelectionActivity) getActivity()).loadFragment(cleanerListFragment);
        });

        recyclerView.setAdapter(adapter);

        // Check if a category was passed and load the corresponding cleaners
        String category = getArguments().getString("category");
        if (category != null) {
            // Optionally, you can directly navigate to the CleanerListFragment here
            CleanerListFragment cleanerListFragment = new CleanerListFragment();
            Bundle args = new Bundle();
            args.putString("category", category);
            cleanerListFragment.setArguments(args);
            ((CategorySelectionActivity) getActivity()).loadFragment(cleanerListFragment);
        }

        return view;
    }
}