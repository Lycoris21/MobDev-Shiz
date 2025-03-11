package com.ph.edu.usc.dejito_day1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<FoodItem> foodItems;
    private List<FoodItem> filteredItems;

    private TextView username;
    private ImageView cart;
    private Spinner categorySpinner;

    private String[] categories = {
            "Pasta", "Desserts", "Drinks"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        username = findViewById(R.id.user);
        username.setText(getIntent().getStringExtra("username"));

        cart = findViewById(R.id.cart);
        cart.setOnClickListener(v -> {
            Intent iCart = new Intent(HomepageActivity.this, CartActivity.class);
            startActivity(iCart);
        });

        categorySpinner = findViewById(R.id.categoryspinner);
        categorySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> arrCategories = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories);
        categorySpinner.setAdapter(arrCategories);

        initializeFoodItems();
        updateUI(foodItems);
    }

    private void initializeFoodItems() {
        foodItems = new ArrayList<>();
        foodItems.add(new FoodItem("Lasagna", 200, 12, "Pasta", "Lasagna is a comforting Italian dish made of pasta, meat sauce, béchamel, and melted cheese, offering a hearty meal with layers of flavor. It's a favorite for family gatherings, combining savory ingredients into a satisfying, filling dish.", R.drawable.lasagna));
        foodItems.add(new FoodItem("Spaghetti", 180, 15, "Pasta", "Spaghetti is a long, thin pasta that pairs perfectly with various sauces, such as marinara or bolognese. Its versatility makes it a go-to dish, delivering a satisfying, flavorful experience with every bite.", R.drawable.spaghetti));
        foodItems.add(new FoodItem("Macaroni", 140, 7, "Pasta", "Macaroni is a short, tube-shaped pasta that’s ideal for creamy, cheesy dishes like macaroni and cheese. Its smooth texture absorbs the sauce, making it a favorite for quick and delicious meals.", R.drawable.macaroni));
        foodItems.add(new FoodItem("Mango Float", 100, 21, "Desserts", "Mango Float is a sweet, no-bake dessert made with layers of graham crackers, whipped cream, and fresh mango slices. The tropical flavor and creamy texture make it a refreshing treat, especially in warm weather.", R.drawable.mangofloat));
        foodItems.add(new FoodItem("Ice Cream", 120, 9, "Desserts", "Ice Cream is a beloved frozen dessert available in countless flavors, offering a creamy, cold treat. Whether in a cone or bowl, it’s the perfect indulgence for any occasion.", R.drawable.icecream));
        foodItems.add(new FoodItem("Coffee Jelly", 150, 17, "Desserts", "Coffee Jelly is a dessert made from coffee-flavored gelatin, often served with cream or milk. It combines the rich taste of coffee with a fun, chewy texture, perfect for coffee enthusiasts.", R.drawable.coffeejelly));
        foodItems.add(new FoodItem("Leche Flan", 80, 8, "Desserts", "Leche Flan is a smooth custard dessert made from eggs, milk, and sugar, topped with caramel syrup. Its rich, creamy texture and sweet flavor make it a beloved Filipino treat.", R.drawable.lecheflan));
        foodItems.add(new FoodItem("Frappe", 120, 12, "Drinks", "Frappe is a chilled, blended coffee drink that’s frothy and refreshing, often made with instant coffee and ice. It’s perfect for those seeking a cool, energizing beverage with a coffee twist.", R.drawable.frappe));
        foodItems.add(new FoodItem("Iced Coffee", 120, 21, "Drinks", "Iced Coffee is a refreshing version of traditional coffee, chilled and served over ice. It's a great way to enjoy coffee on warm days, offering a bold flavor with a cool finish.", R.drawable.icedcoffee));
        foodItems.add(new FoodItem("Milk Tea", 120, 12, "Drinks", "Milk Tea is a sweet, creamy drink made from tea, milk, and sugar, often paired with chewy tapioca pearls. It’s a popular, refreshing beverage that offers a satisfying combination of tea and sweetness.", R.drawable.milktea));
        foodItems.add(new FoodItem("Tea", 100, 19, "Drinks", "Tea is a simple, timeless beverage made by infusing tea leaves in hot water, served hot or iced. It's perfect for any time of day, offering a soothing and aromatic drink with numerous variations.", R.drawable.tea));
        foodItems.add(new FoodItem("Smoothie", 140, 9, "Drinks", "Smoothies are refreshing drinks made by blending fresh fruits and liquids like milk or juice, often with ice. They're a healthy, nutritious option, perfect for a quick snack or a breakfast boost.", R.drawable.strawberrysmoothie));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String selectedCategory = categories[position];
        filteredItems = new ArrayList<>();

        for (FoodItem item : foodItems) {
            if (item.getCategory().equals(selectedCategory)) {
                filteredItems.add(item);
            }
        }

        updateUI(filteredItems);
        Toast.makeText(getApplicationContext(), "Category: " + selectedCategory, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing
    }

    private void updateUI(List<FoodItem> items) {
        GridLayout itemContainer = findViewById(R.id.itemContainer);
        itemContainer.removeAllViews();

        for (FoodItem item : items) {
            View itemView = getLayoutInflater().inflate(R.layout.item_layout, itemContainer, false);
            ImageView itemImage = itemView.findViewById(R.id.itemImage);
            TextView itemName = itemView.findViewById(R.id.itemName);
            TextView itemPrice = itemView.findViewById(R.id.itemPrice);
            TextView itemQuantity = itemView.findViewById(R.id.itemQuantity);
            Button addToCartButton = itemView.findViewById(R.id.addToCartButton);

            itemImage.setImageResource(item.getImageResource());
            itemName.setText(item.getName());
            itemPrice.setText("P" + item.getPrice() + ".00");
            itemQuantity.setText("Quantity: " + item.getQuantity());

            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iDetails = new Intent(HomepageActivity.this, DetailsActivity.class);
                    iDetails.putExtra("name", item.getName());
                    iDetails.putExtra("price", item.getPrice());
                    iDetails.putExtra("quantity", item.getQuantity());
                    iDetails.putExtra("details", item.getDetails());
                    String imagepath = "android.resource://" + getPackageName() + "/" + item.getImageResource();
                    iDetails.putExtra("imagepath", imagepath);
                    startActivity(iDetails);
                }
            });

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(10, 10, 10, 10);
            itemView.setLayoutParams(params);

            itemContainer.addView(itemView);
        }
    }
}