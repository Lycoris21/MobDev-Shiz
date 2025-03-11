package com.ph.edu.usc.dejito_day1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartItemAdapter extends ArrayAdapter<CartItem> {
    private final Context context;
    private final List<CartItem> cartItems;

    public CartItemAdapter(Context context, List<CartItem> cartItems) {
        super(context, R.layout.cart_item_layout, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.cart_item_layout, parent, false);

        ImageView foodImage = itemView.findViewById(R.id.foodpic);
        TextView name = itemView.findViewById(R.id.name);
        TextView quantity = itemView.findViewById(R.id.quantity);
        TextView price = itemView.findViewById(R.id.price);
        TextView total = itemView.findViewById(R.id.total);

        CartItem cartItem = cartItems.get(position);
        foodImage.setImageURI(Uri.parse(cartItem.getImagePath()));
        name.setText(cartItem.getName());
        quantity.setText("Quantity: " + cartItem.getQuantity());
        price.setText("Price: P" + cartItem.getPrice() + ".00");
        total.setText("Total Price: P" + (cartItem.getPrice() * cartItem.getQuantity()) + ".00");

        return itemView;
    }
}