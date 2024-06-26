package com.example.stuntguard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class BeritaPreviewAdapter extends RecyclerView.Adapter<BeritaPreviewAdapter.ViewHolder> {

    private List<Berita> beritas;
    private OnBeritaClickListener onBeritaClickListener;
    private Context context;

    public interface OnBeritaClickListener {
        void onBeritaClick(Berita berita);
    }

    public BeritaPreviewAdapter(List<Berita> beritas, OnBeritaClickListener onBeritaClickListener, Context context) {
        this.beritas = beritas;
        this.onBeritaClickListener = onBeritaClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Berita berita = beritas.get(position);
        Glide.with(context)
                .load(berita.getImageUrl())
                .into(holder.imageView);
        holder.textViewTitle.setText(berita.getTitle());
        holder.bind(berita, onBeritaClickListener);
    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }

        public void bind(final Berita berita, final OnBeritaClickListener listener) {
            itemView.setOnClickListener(v -> listener.onBeritaClick(berita));
        }
    }
}
