package com.example.djo18.samplepagergallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class GalleryFragment extends Fragment {
    private static final String ARG_GALLERY = "gallery";

    RecyclerView recyclerView;
    GalleryAdapter adapter;


    private ParcelableGallery parcelableGallery;


    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment newInstance(ParcelableGallery gallery) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_GALLERY, gallery);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parcelableGallery = getArguments().getParcelable(ARG_GALLERY);
        }

        adapter = new GalleryAdapter(parcelableGallery, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.gallery_recycler);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}
