package com.ph.edu.usc.dejito_day1;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CounterActivity extends AppCompatActivity {

    Button minus, add, prev;
    TextView counter, passedName, passedCourse, passedYear, passedWham;
    ImageView passedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_counter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        counter = findViewById(R.id.counter);
        minus = findViewById(R.id.minus);
        add = findViewById(R.id.add);
        prev = findViewById(R.id.prev);

        passedName = findViewById(R.id.passedname);
        passedName.setText(getIntent().getStringExtra("name"));
        passedCourse= findViewById(R.id.passedcourse);
        passedCourse.setText(getIntent().getStringExtra("course"));
        passedYear = findViewById(R.id.passedyear);
        passedYear.setText(getIntent().getStringExtra("year"));
        passedWham = findViewById(R.id.passedwham);
        passedWham.setText(getIntent().getStringExtra("wham"));
        passedImage = findViewById(R.id.passedimage);
        passedImage.setImageURI(Uri.parse(getIntent().getStringExtra("imagefp")));

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = new Integer(counter.getText().toString());
                if (count > 0){
                    count--;
                }
                counter.setText(String.valueOf(count));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = new Integer(counter.getText().toString());
                count++;
                counter.setText(String.valueOf(count));
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iMain= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(iMain);
            }
        });
    }
}