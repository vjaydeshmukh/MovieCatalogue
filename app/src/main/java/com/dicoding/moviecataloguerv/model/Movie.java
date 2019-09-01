package com.dicoding.moviecataloguerv.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private String title;
    private String desc;
    private String poster;
    private String release;

    public Movie() {

    }

    private Movie(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.poster = in.readString();
        this.release = in.readString();
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.poster);
        dest.writeString(this.release);
    }
}
