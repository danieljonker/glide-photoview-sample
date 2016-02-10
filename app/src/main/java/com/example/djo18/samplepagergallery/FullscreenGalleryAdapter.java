package com.example.djo18.samplepagergallery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class FullscreenGalleryAdapter extends FragmentStatePagerAdapter {

    private final List<ParcelableGallery.ParcelableGalleryItem> galleryItems;

    public FullscreenGalleryAdapter(FragmentManager fm, ParcelableGallery gallery) {
        super(fm);
        galleryItems = gallery.getGalleryItems();
    }

    @Override
    public Fragment getItem(int position) {
        ParcelableGallery.ParcelableGalleryItem item = galleryItems.get(position);
        String stringPosition = String.format("%d/%d", position + 1, galleryItems.size());
        return FullscreenImageFragment.newInstance(item.getUrl(), item.getCaption(), stringPosition);
    }

    @Override
    public int getCount() {
        return galleryItems.size();
    }
}
