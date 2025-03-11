package com.ph.edu.usc.dejito_day1;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends AppCompatActivity {

    Button minus, add, addtocart, checkout;
    TextView counter, name, details, foodprice, foodavailable;
    ImageView foodimage, back;
    int price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.foodname);
        name.setText(getIntent().getStringExtra("name"));

        price = getIntent().getIntExtra("price", 0);
        foodprice = findViewById(R.id.price);
        foodprice.setText("P" + price + ".00");

        quantity = getIntent().getIntExtra("quantity", 0);
        foodavailable = findViewById(R.id.foodavailable);
        foodavailable.setText(String.valueOf(quantity));

        details = findViewById(R.id.fooddetails);
        details.setText(getIntent().getStringExtra("details"));

        foodimage = findViewById(R.id.foodimage);
        foodimage.setImageURI(Uri.parse(getIntent().getStringExtra("imagepath")));

        counter = findViewById(R.id.counter);
        minus = findViewById(R.id.minus);
        add = findViewById(R.id.add);

        addtocart = findViewById(R.id.addtocart);
        checkout = findViewById(R.id.cart);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHomepage = new Intent(DetailsActivity.this, HomepageActivity.class);
                startActivity(iHomepage);
            }
        });





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
                if(count<quantity){
                    count++;
                }
                counter.setText(String.valueOf(count));
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(counter.getText().toString());
                if (count > 0) {
                    CartItem newItem = new CartItem(
                            name.getText().toString(),
                            price,
                            count,
                            getIntent().getStringExtra("imagepath")
                    );

                    CartManager.getInstance().addItem(newItem);
                    counter.setText("0");
                    int remaining = Integer.valueOf(foodavailable.getText().toString()) - count;
                    quantity = remaining;
                    foodavailable.setText(String.valueOf(remaining));
                    Toast.makeText(DetailsActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCart = new Intent(DetailsActivity.this, CartActivity.class);
                startActivity(iCart);
            }
        });

    }
}