package com.ph.edu.usc.dejito_midterm;

import android.os.Bundle;
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

public class CategorySelectionFragment extends Fragment {

    private List<Category> categories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_selection, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.allCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        JSONArray categoryArray;

        try {
            JSONObject categoryObj = new JSONObject(loadJSONFromAsset("category_list.json"));
            categoryArray = categoryObj.getJSONArray("categories");

            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject categoryDetail = categoryArray.getJSONObject(i);
                categories.add(new Category(
                        categoryDetail.getString("name"),
                        categoryDetail.getString("description"),
                        categoryDetail.getString("imageResource")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        CategoryAdapter adapter = new CategoryAdapter(categories, category -> {
            CleanerListFragment cleanerListFragment = new CleanerListFragment();
            Bundle args = new Bundle();
            args.putString("category", category.getName());
            cleanerListFragment.setArguments(args);
            ((CategorySelectionActivity) getActivity()).loadFragment(cleanerListFragment);
        });

        recyclerView.setAdapter(adapter);

        String category = getArguments().getString("category");
        if (category != null) {
            CleanerListFragment cleanerListFragment = new CleanerListFragment();
            Bundle args = new Bundle();
            args.putString("category", category);
            cleanerListFragment.setArguments(args);
            ((CategorySelectionActivity) getActivity()).loadFragment(cleanerListFragment);
        }

        return view;
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
}