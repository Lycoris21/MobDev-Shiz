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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategories;
    private RecyclerView recyclerViewTopCleaners;
    private EditText searchEditText;
    private CategoryAdapter categoryAdapter;
    private CleanerAdapter cleanerAdapter;

    private List<Category> categoryList = new ArrayList<>();
    private List<Cleaner> allCleaners = new ArrayList<>();
    private List<Cleaner> filteredCleaners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategories = findViewById(R.id.categories);
        recyclerViewTopCleaners = findViewById(R.id.topCleaners);
        searchEditText = findViewById(R.id.search);
        LinearLayout moreCategories = findViewById(R.id.more);

        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        JSONArray categoryArray;
        JSONArray cleanerArray;

        try {
            JSONObject categoryObj = new JSONObject(loadJSONFromAsset("category_list.json"));
            categoryArray = categoryObj.getJSONArray("categories");

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject categoryDetail = categoryArray.getJSONObject(i);
                categoryList.add(new Category(
                        categoryDetail.getString("name"),
                        categoryDetail.getString("description"),
                        categoryDetail.getString("imageResource")
                ));
            }

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

        categoryAdapter = new CategoryAdapter(categoryList, category -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectionActivity.class);
            intent.putExtra("category", category.getName());
            startActivity(intent);
        });
        recyclerViewCategories.setAdapter(categoryAdapter);

        recyclerViewTopCleaners.setLayoutManager(new LinearLayoutManager(this));
        cleanerAdapter = new CleanerAdapter(allCleaners, cleaner -> {
            Intent intent = new Intent(MainActivity.this, CleanerProfileActivity.class);
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
        recyclerViewTopCleaners.setAdapter(cleanerAdapter);

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

        moreCategories.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CategorySelectionActivity.class);
            startActivity(intent);
        });
    }

    private void filterCleaners(String query) {
        if (query.isEmpty()) {
            cleanerAdapter.updateList(allCleaners);
            return;
        }
        filteredCleaners.clear();
        for (Cleaner cleaner : allCleaners) {
            if (cleaner.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCleaners.add(cleaner);
            }
        }
        cleanerAdapter.updateList(filteredCleaners);
    }


    private String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
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
