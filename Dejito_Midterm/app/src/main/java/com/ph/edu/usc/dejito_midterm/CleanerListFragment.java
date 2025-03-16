package com.ph.edu.usc.dejito_midterm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CleanerListFragment extends Fragment {

    private List<Cleaner> allCleaners;
    private List<Cleaner> filteredCleaners;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cleaner_list, container, false);

        RecyclerView cleanerListView = view.findViewById(R.id.cleanerList);
        cleanerListView.setLayoutManager(new LinearLayoutManager(getContext())); // Set the layout manager

        initializeCleaners(); // Initialize the list of cleaners

        // Get the selected category from arguments
        String selectedCategory = getArguments().getString("category");

        // Filter the cleaners based on the selected category
        filteredCleaners = filterCleanersByCategory(selectedCategory);

        // Create the adapter and set it to the RecyclerView
        CleanerAdapter adapter = new CleanerAdapter(filteredCleaners, cleaner -> {
            Intent intent = new Intent(getActivity(), CleanerProfileActivity.class);
            intent.putExtra("cleaner_name", cleaner.getName());
            intent.putExtra("cleaner_gender", cleaner.getGender());
            intent.putExtra("cleaner_details", cleaner.getDetails());
            intent.putExtra("cleaner_rating", cleaner.getRating());
            intent.putExtra("cleaner_image", cleaner.getImageResource());
            intent.putStringArrayListExtra("cleaner_services", new ArrayList<>(cleaner.getCategory()));
            startActivity(intent);
        });
        Log.d("CleanerListFragment", "Filtered cleaners count: " + filteredCleaners.size());
        cleanerListView.setAdapter(adapter); // Set the adapter

        return view;
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

    private List<Cleaner> filterCleanersByCategory(String category) {
        List<Cleaner> filtered = new ArrayList<>();
        for (Cleaner cleaner : allCleaners) {
            Log.d("CleanerListFragment", "Checking cleaner: " + cleaner.getName() + " with services: " + cleaner.getCategory());
            if (cleaner.getCategory().contains(category)) {
                filtered.add(cleaner);
            }
        }
        Log.d("CleanerListFragment", "Filtered cleaners count: " + filtered.size());
        return filtered;
    }
}