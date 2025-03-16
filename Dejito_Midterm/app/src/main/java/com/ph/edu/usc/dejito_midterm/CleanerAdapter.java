package com.ph.edu.usc.dejito_midterm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CleanerAdapter extends RecyclerView.Adapter<CleanerAdapter.ViewHolder> {
    private List<Cleaner> cleaners;
    private final OnCleanerClickListener listener;

    public interface OnCleanerClickListener {
        void onCleanerClick(Cleaner cleaner);
    }

    public CleanerAdapter(List<Cleaner> cleaners, OnCleanerClickListener listener) {
        this.cleaners = cleaners;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cleaner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cleaner cleaner = cleaners.get(position);
        holder.cleanerName.setText(cleaner.getName());
        holder.cleanerDetails.setText(cleaner.getDetails() + " | Rating: " + cleaner.getRating());
        holder.cleanerImage.setImageResource(cleaner.getImageResource());
        holder.itemView.setOnClickListener(v -> listener.onCleanerClick(cleaner));
    }

    @Override
    public int getItemCount() {
        return cleaners.size();
    }

    public void updateList(List<Cleaner> newCleaners) {
        this.cleaners = newCleaners;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cleanerName;
        TextView cleanerDetails;
        ImageView cleanerImage;

        ViewHolder(View itemView) {
            super(itemView);
            cleanerName = itemView.findViewById(R.id.cleanerName);
            cleanerDetails = itemView.findViewById(R.id.cleanerDetails);
            cleanerImage = itemView.findViewById(R.id.cleanerImage);
        }
    }
}