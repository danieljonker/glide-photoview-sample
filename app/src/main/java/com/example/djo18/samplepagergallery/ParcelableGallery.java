package com.example.djo18.samplepagergallery;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ParcelableGallery implements Parcelable {
    private int mGalleryId;
    private List<ParcelableGalleryItem> mGalleryItems;

    public ParcelableGallery(int mGalleryId, List<ParcelableGalleryItem> mGalleryItems) {
        this.mGalleryId = mGalleryId;
        this.mGalleryItems = mGalleryItems;
    }

    public ParcelableGallery(Parcel in) {
        this.mGalleryId = in.readInt();
        this.mGalleryItems = new ArrayList<>();
        in.readTypedList(this.mGalleryItems, ParcelableGalleryItem.CREATOR);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mGalleryId);
        dest.writeTypedList(mGalleryItems);
    }

    public int describeContents() {
        return 0;
    }

    public List<ParcelableGalleryItem> getGalleryItems() {
        return mGalleryItems;
    }

    public int getGalleryId() {
        return mGalleryId;
    }

    static public final Parcelable.Creator<ParcelableGallery> CREATOR
            = new Parcelable.Creator<ParcelableGallery>() {

        public ParcelableGallery createFromParcel(Parcel in) {
            return new ParcelableGallery(in);
        }

        public ParcelableGallery[] newArray(int size) {
            return new ParcelableGallery[size];
        }
    };

    static public class ParcelableGalleryItem implements Parcelable {
        private String mCaption;
        private String mUrl;

        public ParcelableGalleryItem(Parcel in) {
            this.mCaption = in.readString();
            this.mUrl = in.readString();
        }

        public ParcelableGalleryItem(String mCaption, String mUrl) {
            this.mCaption = mCaption;
            this.mUrl = mUrl;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(mCaption);
            dest.writeString(mUrl);
        }

        public String getUrl() {
            return mUrl;
        }

        public String getCaption() {
            return mCaption;
        }

        static final Parcelable.Creator<ParcelableGalleryItem> CREATOR
                = new Parcelable.Creator<ParcelableGalleryItem>() {

            public ParcelableGalleryItem createFromParcel(Parcel in) {
                return new ParcelableGalleryItem(in);
            }

            public ParcelableGalleryItem[] newArray(int size) {
                return new ParcelableGalleryItem[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }
    }
}

