package com.ph.edu.usc.dejito_day1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ImageView home;
    List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        home = findViewById(R.id.home);
        cartItems = CartManager.getInstance().getItems();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHomepage = new Intent(CartActivity.this, HomepageActivity.class);
                startActivity(iHomepage);
            }
        });

        if (cartItems == null || cartItems.isEmpty()) {
            TextView emptyCart = findViewById(R.id.emptycart);
            Button checkoutBtn = findViewById(R.id.checkoutbtn);
            emptyCart.setVisibility(View.VISIBLE);
            checkoutBtn.setVisibility(View.GONE);
        }

        displayCartItems();
    }

    private void displayCartItems() {
        ListView cartListView = findViewById(R.id.cartListView);
        CartItemAdapter adapter = new CartItemAdapter(this, cartItems);
        cartListView.setAdapter(adapter);
    }
}