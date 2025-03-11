package com.ph.edu.usc.dejito_day1;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView name, course, year, wham;
    ImageView jingliu;
    Button breh, next, browse;
    EditText sName, sCourse, sYear, sWham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sName = findViewById(R.id.sName);
        sCourse = findViewById(R.id.sCourse);
        sYear =  findViewById(R.id.sYear);
        sWham = findViewById(R.id.sWham);

        name = (TextView)findViewById(R.id.name);
        course = (TextView)findViewById(R.id.course);
        year = (TextView)findViewById(R.id.year);
        wham = (TextView)findViewById(R.id.wham);

        jingliu = (ImageView)findViewById(R.id.jingliu);

        breh = (Button)findViewById(R.id.breh);
        next = (Button)findViewById(R.id.next);
        browse = findViewById(R.id.browse);

        breh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jingliu.setImageResource(R.drawable.jingliu);
                name.setText(sName.getText().toString());
                course.setText(sCourse.getText().toString());
                year.setText(sYear.getText().toString());
                wham.setText(sWham.getText().toString());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageFilePath = "android.resource://" + getPackageName() + "/drawable/jingliu";

                Intent iCounter = new Intent(MainActivity.this, CounterActivity.class);
                iCounter.putExtra("name", name.getText().toString());
                iCounter.putExtra("course", course.getText().toString());
                iCounter.putExtra("year", year.getText().toString());
                iCounter.putExtra("wham", wham.getText().toString());
                iCounter.putExtra("imagefp", imageFilePath);

                startActivity(iCounter);
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iBrowse = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("tel: 09123456789"));
                startActivity(iBrowse);
            }
        });
    }

}