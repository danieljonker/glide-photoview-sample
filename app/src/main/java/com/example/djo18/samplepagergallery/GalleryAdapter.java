package com.example.djo18.samplepagergallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    ParcelableGallery gallery;
    Context context;

    public GalleryAdapter() {
    }

    public GalleryAdapter(ParcelableGallery gallery, Context context) {
        this.gallery = gallery;
        this.context = context;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exploded_gallery_item, parent, false);
        GalleryViewHolder viewHolder = new GalleryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        ParcelableGallery.ParcelableGalleryItem item = gallery.getGalleryItems().get(position);
        Glide.with(context.getApplicationContext())
                .load(item.getUrl())
                .into(holder.imageView);

        holder.caption.setText(item.getCaption());
    }

    @Override
    public int getItemCount() {
        return gallery.getGalleryItems().size();
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView caption;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.gallery_image);
            caption = (TextView) itemView.findViewById(R.id.gallery_caption);
        }
    }
}
