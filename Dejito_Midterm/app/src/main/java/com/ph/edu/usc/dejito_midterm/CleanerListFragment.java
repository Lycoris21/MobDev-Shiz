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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CleanerListFragment extends Fragment {

    private List<Cleaner> allCleaners = new ArrayList<>();
    private List<Cleaner> filteredCleaners = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cleaner_list, container, false);

        RecyclerView cleanerListView = view.findViewById(R.id.cleanerList);
        cleanerListView.setLayoutManager(new LinearLayoutManager(getContext()));


        JSONArray cleanerArray;

        try {
            JSONObject cleanerObj = new JSONObject(loadJSONFromAsset("cleaner_list.json"));
            cleanerArray = cleanerObj.getJSONArray("cleaners");

            for (int i = 0; i < cleanerArray.length(); i++) {
                JSONObject cleanerDetail = cleanerArray.getJSONObject(i);
                allCleaners.add(new Cleaner(
                        cleanerDetail.getInt("id"),
                        cleanerDetail.getString("name"),
                        cleanerDetail.getInt("age"),
                        cleanerDetail.getString("gender"),
                        cleanerDetail.getString("details"),
                        cleanerDetail.getString("sched"),
                        cleanerDetail.getString("address"),
                        cleanerDetail.getString("number"),
                        (float) cleanerDetail.getDouble("rating"),
                        cleanerDetail.getString("imageResource"),
                        cleanerDetail.getInt("cleanRate"),
                        cleanerDetail.getInt("attitudeRate"),
                        cleanerDetail.getInt("satisfactionRate"),
                        parseJSONArrayToList(cleanerDetail.getJSONArray("services"))
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        allCleaners.sort((c1, c2) -> Float.compare(c2.getRating(), c1.getRating()));

        String selectedCategory = getArguments().getString("category");

        filteredCleaners = filterCleanersByCategory(selectedCategory);

        CleanerAdapter adapter = new CleanerAdapter(filteredCleaners, cleaner -> {
            Intent intent = new Intent(getActivity(), CleanerProfileActivity.class);
            intent.putExtra("cleaner_id", cleaner.getId());
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

    private String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private List<String> parseJSONArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}