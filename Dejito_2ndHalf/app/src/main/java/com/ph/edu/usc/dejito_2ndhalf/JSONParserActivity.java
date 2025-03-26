package com.ph.edu.usc.dejito_2ndhalf;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONParserActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> personName = new ArrayList<>();
    ArrayList<String> personPhone = new ArrayList<>();
    ArrayList<String> personEmail = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jsonparser);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        JSONArray userArray = null;

        try{
            JSONObject obj = new JSONObject(loadJSONDFromAsset()); // get JSONObject
            userArray = obj.getJSONArray("users"); // fetch data

            for(int i = 0; i < userArray.length(); i++){
                JSONObject userDetail = userArray.getJSONObject(i);
                personName.add(userDetail.getString("name"));
                personPhone.add(userDetail.getString("phone"));
                personEmail.add(userDetail.getString("email"));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        CustomAdapter customAdapter = new CustomAdapter(JSONParserActivity.this, personName, personPhone, personEmail);
        recyclerView.setAdapter(customAdapter);

    }

    private String loadJSONDFromAsset() {
        String json = null;

        try {
            InputStream is = getAssets().open("user_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }

        return json;
    }
}