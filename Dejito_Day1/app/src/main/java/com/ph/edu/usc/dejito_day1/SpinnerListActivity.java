package com.ph.edu.usc.dejito_day1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SpinnerListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner listSpin;

    ListView list;

    String[] courses = {
            "Android", "Java","Python", "C Programming", "Angular"
    };

    String[] topics = {
            "Arrays", "Linked List", "Hashing", "Trees", "Graphs"
    };

    Integer[] imgid = {
            R.drawable.jingliu,
            R.drawable.lasagna,
            R.drawable.mangofloat,
            R.drawable.lecheflan,
            R.drawable.coffeejelly,
            R.drawable.icecream
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listSpin = findViewById(R.id.listspin);
        list = findViewById(R.id.list);

        listSpin.setOnItemSelectedListener(this);
        ArrayAdapter<String> arrCourses = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, courses);
        listSpin.setAdapter(arrCourses);

        MyListAdapter adapter = new MyListAdapter(this, courses, topics, imgid);
        list.setAdapter(adapter);

//        ArrayAdapter<String> arrTopics = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_1, topics);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}