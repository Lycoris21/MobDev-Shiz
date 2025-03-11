package com.ph.edu.usc.dejito_day1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    String uname = "Lycoris";
    String pass = "1234";

    EditText username, password;
    Button login;
    TextView signup, error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login = findViewById(R.id.login2);
        signup = findViewById(R.id.signup3);
        error = findViewById(R.id.error);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals(uname) && password.getText().toString().equals(pass)) {
                    Intent iHomepage = new Intent(LoginActivity.this, HomepageActivity.class);
                    iHomepage.putExtra("username", username.getText().toString());
                    startActivity(iHomepage);
                }else{
                    error.setText("Invalid credentials");
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSignup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(iSignup);
            }
        });

    }
}