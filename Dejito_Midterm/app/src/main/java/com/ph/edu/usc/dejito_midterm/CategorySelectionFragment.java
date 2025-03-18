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

        categories = new ArrayList<>();
        categories.add(new Category("Cleaning Service", "Professional cleaning services for your home.", R.drawable.cleaning));
        categories.add(new Category("Cleaning Appliance", "Services for cleaning appliances.", R.drawable.appliance));
        categories.add(new Category("Babysitter", "Trusted babysitting services for your children.", R.drawable.babysitter));

        RecyclerView recyclerView = view.findViewById(R.id.allCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

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
}