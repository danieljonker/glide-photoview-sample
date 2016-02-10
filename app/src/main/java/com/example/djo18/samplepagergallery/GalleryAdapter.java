package com.example.djo18.samplepagergallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    ParcelableGallery gallery;
    Context context;

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
        holder.addImage(gallery, position, context);
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

        public void addImage(final ParcelableGallery gallery, final int position, Context context) {
            ParcelableGallery.ParcelableGalleryItem galleryItem = gallery.getGalleryItems().get(position);
            if (galleryItem != null) {
                Glide.with(context)
                        .load(galleryItem.getUrl())
                        .placeholder(R.drawable.default_teaser_16x9)
                        .into(imageView);

                //Add click listener
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("blah", "CLICK");
                        Intent intent = FullscreenGalleryActivity.getIntent(v.getContext(), gallery, position);
                        v.getContext().startActivity(intent);
                    }
                });

                caption.setText(galleryItem.getCaption());
            }
        }
    }
}
