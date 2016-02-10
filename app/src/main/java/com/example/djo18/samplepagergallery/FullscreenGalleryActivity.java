package com.example.djo18.samplepagergallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FullscreenGalleryActivity extends AppCompatActivity {
    public static final String INTENTEXTRA_GALLERY = "gallery";
    public static final String INTENTEXTRA_POSITION = "position";

    private final Handler mHideHandler = new Handler();

//    @Bind(R.id.fullscreen_gallery_pager)
    public ViewPager pager;

//    @Inject
//    RxBus rxBus;

    private boolean immersive;

    private ParcelableGallery gallery;
    private int startingPosition;

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            pager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
    };

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_gallery);

        pager = (ViewPager) findViewById(R.id.fullscreen_gallery_pager);

        immersive = false;

        Intent intent = getIntent();
        gallery = intent.getParcelableExtra(INTENTEXTRA_GALLERY);
        startingPosition = intent.getIntExtra(INTENTEXTRA_POSITION, 0);

        pager.setAdapter(new FullscreenGalleryAdapter(getSupportFragmentManager(), gallery));
        pager.setCurrentItem(startingPosition);

//        show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void toggle() {
//        rxBus.send(new FullscreenGalleryImmersiveEvent(immersive));
        if (immersive) {
            show();
        } else {
            hide();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        immersive = true;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.post(mHidePart2Runnable);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        pager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        immersive = false;
        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.post(mShowPart2Runnable);
    }

    public static Intent getIntent(Context context, ParcelableGallery gallery, int position) {
        Intent intent = new Intent(context, FullscreenGalleryActivity.class);
        intent.putExtra(INTENTEXTRA_GALLERY, gallery);
        intent.putExtra(INTENTEXTRA_POSITION, position);
        return intent;
    }

    public boolean isImmersive() {
        return immersive;
    }
}
