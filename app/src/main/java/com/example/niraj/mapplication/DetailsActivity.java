package com.example.niraj.mapplication;

import android.os.Parcel;
import android.os.Parcelable;


public class DetailsActivity implements Parcelable {
    String mSOverview;
    String mSTitle;
    String mSRel_date;
    String mSOriginal_Title;
    String mSLanguage;


    public DetailsActivity(String sOverview, String sTitle, String sRel_date, String sOriginal_title,
                           String sLanguage) {
        this.mSOverview = sOverview;
        this.mSTitle = sTitle;
        this.mSRel_date = sRel_date;
        this.mSOriginal_Title=sOriginal_title;
        this.mSLanguage=sLanguage;



    }

    protected DetailsActivity(Parcel in) {
        mSOverview = in.readString();
        mSTitle = in.readString();
        mSRel_date = in.readString();
        mSOriginal_Title=in.readString();
        mSLanguage=in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSOverview);
        dest.writeString(mSTitle);
        dest.writeString(mSRel_date);
        dest.writeString(mSOriginal_Title);
        dest.writeString(mSLanguage);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetailsActivity> CREATOR = new Creator<DetailsActivity>() {
        @Override
        public DetailsActivity createFromParcel(Parcel in) {
            return new DetailsActivity(in);
        }

        @Override
        public DetailsActivity[] newArray(int size) {
            return new DetailsActivity[size];
        }
    };


}
