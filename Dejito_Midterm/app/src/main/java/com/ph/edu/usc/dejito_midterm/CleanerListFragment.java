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
        cleanerListView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeCleaners();

        String selectedCategory = getArguments().getString("category");

        filteredCleaners = filterCleanersByCategory(selectedCategory);

        CleanerAdapter adapter = new CleanerAdapter(filteredCleaners, cleaner -> {
            Intent intent = new Intent(getActivity(), CleanerProfileActivity.class);
            intent.putExtra("cleaner_name", cleaner.getName());
            intent.putExtra("cleaner_age", cleaner.getAge());
            intent.putExtra("cleaner_gender", cleaner.getGender());
            intent.putExtra("cleaner_details", cleaner.getDetails());
            intent.putExtra("cleaner_sched", cleaner.getSchedule());
            intent.putExtra("cleaner_address", cleaner.getAddress());
            intent.putExtra("cleaner_number", cleaner.getNumber());
            intent.putExtra("cleaner_rating", cleaner.getRating());
            intent.putExtra("cleaner_image", cleaner.getImageResource());
            intent.putExtra("cleaner_clean", cleaner.getCleanRate());
            intent.putExtra("cleaner_attitude", cleaner.getAttitudeRate());
            intent.putExtra("cleaner_satisfaction", cleaner.getSatisfactionRate());
            intent.putStringArrayListExtra("cleaner_services", new ArrayList<>(cleaner.getCategory()));
            startActivity(intent);
        });
        Log.d("CleanerListFragment", "Filtered cleaners count: " + filteredCleaners.size());
        cleanerListView.setAdapter(adapter);

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

        allCleaners.add(new Cleaner("Cleaner 1", 18,"Female", "Experienced in house cleaning", "Weekends","Mandaue","09123456789",4.5f, R.drawable.jingliu, services1, 40, 60, 50));
        allCleaners.add(new Cleaner("Cleaner 2", 24,"Male", "Specializes in garden maintenance", "Weekdays","Cabancalan","09123456789",4.0f, R.drawable.jingliu, services2, 56, 64, 59));
        allCleaners.add(new Cleaner("Cleaner 3", 35,"Female", "Expert deep cleaner with 10 years of experience", "Mon-Fri", "Talamban","09123456789",5.0f, R.drawable.jingliu, services3, 89, 76, 83));
        allCleaners.add(new Cleaner("Cleaner 4", 28,"Male", "Certified babysitter", "MWF", "Guadalupe","09123456789",4.8f, R.drawable.jingliu, services4, 96, 67, 89));
        allCleaners.add(new Cleaner("Cleaner 5", 30,"Male", "Certified babysitter", "TTH" , "Mabolo","09123456789",4.8f, R.drawable.jingliu, services4, 23, 34, 27));
        allCleaners.add(new Cleaner("Cleaner 6", 21,"Male", "Certified babysitter","Mondays" , "Maguikay","09123456789",4.8f, R.drawable.jingliu, services4, 67, 45, 56));
        allCleaners.add(new Cleaner("Cleaner 7", 43,"Male", "Certified babysitter", "Sundays", "SRP","09123456789",4.8f, R.drawable.jingliu, services4, 60, 50, 55));

        allCleaners.sort((c1, c2) -> Float.compare(c2.getRating(), c1.getRating()));
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