package com.ph.edu.usc.dejito_2ndhalf;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SQLAppActivity extends AppCompatActivity {

    TextView dataTextView;
    EditText username, password, deleteName, currName, newName;
    Button viewData, addUser, deleteData, editData;

    myDBAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlapp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        deleteName = findViewById(R.id.deletename);
        currName = findViewById(R.id.currname);
        newName = findViewById(R.id.newname);

        viewData = findViewById(R.id.viewdata);
        addUser = findViewById(R.id.adduser);
        deleteData = findViewById(R.id.deletedata);
        editData = findViewById(R.id.editData);

        dataTextView = findViewById(R.id.dataTextView);

        helper = new myDBAdapter(this);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(v);
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData(v);
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(v);
            }
        });

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData(v);
            }
        });
    }

    public  void addUser(View view){

       String name = username.getText().toString();
       String pass = password.getText().toString();
       if(name.isEmpty() || pass.isEmpty()){
           Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
       }else{
           long id = helper.insertData(name, pass);
           if(id <= 0){
               Toast.makeText(this, "Data insertion was unsuccessful!", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(this, "Data insertion was successful!", Toast.LENGTH_SHORT).show();
               username.setText("");
               password.setText("");
           }
       }
    }

    public  void viewData(View view){
        String data = helper.getData();
        dataTextView.setText(data);
    }

    public  void deleteData(View view){
        String uname = deleteName.getText().toString();
        if(uname.isEmpty()){
            Toast.makeText(getApplicationContext(), "Field must not be empty", Toast.LENGTH_LONG).show();
        }else{
            int uuname = helper.deleteData(uname);
            if(uuname <= 0){
                Toast.makeText(getApplicationContext(), "Deletion Unsuccessful", Toast.LENGTH_LONG).show();
                deleteName.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                deleteName.setText("");
            }
        }
    }

    public  void editData(View view){
        String nname = newName.getText().toString();
        String cname = currName.getText().toString();
        if(nname.isEmpty() || cname.isEmpty()){
            Toast.makeText(getApplicationContext(), "Fields must not be empty", Toast.LENGTH_LONG).show();
        }else{
            int uuname = helper.updateName(cname, nname);
            if(uuname <= 0){
                Toast.makeText(getApplicationContext(), "Update Unsuccessful", Toast.LENGTH_LONG).show();
                newName.setText("");
                currName.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                newName.setText("");
                currName.setText("");
            }
        }
    }
}