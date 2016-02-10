package com.example.djo18.samplepagergallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import uk.co.senab.photoview.PhotoViewAttacher;

public class FullscreenImageFragment extends Fragment {
    private static final float MEDIUM_SCALE = 1.5f;

    ImageView imageView;
    View captionLayout;
//    TextView imagePosition;
    TextView captionText;

    PhotoViewAttacher attacher;

    private static final String ARG_URL = "url";
    private static final String ARG_CAPTION = "caption";
    private static final String ARG_POSITION = "position";

    private String url;
    private String caption;
    private String position;

    public FullscreenImageFragment() {
    }

    public static FullscreenImageFragment newInstance(String url, String caption, String position) {
        FullscreenImageFragment fragment = new FullscreenImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        args.putString(ARG_CAPTION, caption);
        args.putString(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            url = getArguments().getString(ARG_URL);
            caption = getArguments().getString(ARG_CAPTION);
            position = getArguments().getString(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fullscreen_image, container, false);

        imageView = (ImageView) view.findViewById(R.id.fullscreen_image);
        captionLayout = view.findViewById(R.id.caption_layout);
        captionText = (TextView) view.findViewById(R.id.caption_text);


        if (((FullscreenGalleryActivity) getActivity()).isImmersive())
            captionLayout.setVisibility(View.INVISIBLE);
        else
            captionLayout.setVisibility(View.VISIBLE);

        captionText.setText(caption);

        final PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);


        Glide.with(getContext()).load(url)
                .placeholder(R.drawable.default_teaser_16x9)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (attacher != null) {
                            Log.d("blah", "resourceReady");
//                            attacher.update();
                        }

                        return false;
                    }
                }).into(imageView);

        attacher.setMediumScale(MEDIUM_SCALE);

        attacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {

            @Override
            public void onViewTap(View view, float x, float y) {
                imageClick();
            }
        });

        return view;
    }

    private void imageClick() {
        ((FullscreenGalleryActivity) getActivity()).toggle();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (subscription == null) {
//            subscription = rxBus.toObserverable()
//                    .observeOn(androidMainThreadScheduler)
//                    .subscribe(event -> busEvent(event), throwable -> displayError(throwable));
//        }
    }

//    public void busEvent(Object event) {
//        if (event instanceof FullscreenGalleryImmersiveEvent) {
//            boolean invisible = ((FullscreenGalleryImmersiveEvent) event).isImmersive();
//            if (invisible)
//                captionLayout.setVisibility(View.VISIBLE);
//            else
//                captionLayout.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    private void displayError(Throwable throwable) {
//        Timber.w(throwable, "Can't toggle caption in fullscreen image gallery");
//    }

    public PhotoViewAttacher getAttacher() {
        return attacher;
    }
}
