package com.example.djo18.samplepagergallery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, GalleryFragment.newInstance(buildGallery(1234, 10))).commit();
    }


    private ParcelableGallery buildGallery(int id, int size) {
        List<ParcelableGallery.ParcelableGalleryItem> items = new ArrayList<>();

        for (int i = 0; i <= size; i++) {
            items.add(new ParcelableGallery.ParcelableGalleryItem("caption" + i, "http://media.skynews.com/media/images/generated/2015/11/1/427424/default/v1/gettyimages-495222500-1-774x580.jpg"));
        }

        return new ParcelableGallery(id, items);

    }


}
