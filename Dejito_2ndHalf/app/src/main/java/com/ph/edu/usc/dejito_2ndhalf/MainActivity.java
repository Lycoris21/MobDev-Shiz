package com.ph.edu.usc.dejito_2ndhalf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name, phone, email;
    Button save;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedPreferences;

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

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        save = findViewById(R.id.save);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        getData();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namae = name.getText().toString();
                String denwa = phone.getText().toString();
                String meru = email.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Name, namae);
                editor.putString(Phone, denwa);
                editor.putString(Email, meru);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Data has been saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getData(){
        if(sharedPreferences.contains(Name)){
            name.setText(sharedPreferences.getString(Name, ""));
        }
        if(sharedPreferences.contains(Phone)){
            phone.setText(sharedPreferences.getString(Phone, ""));
        }
        if(sharedPreferences.contains(Email)){
            email.setText(sharedPreferences.getString(Email, ""));
        }
    }
}