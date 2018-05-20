package com.android.udmusicplayer;

public class Song {

    private String mSongTitle;
    private String mArtistName;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Song(String mSongTitle, String mArtistName, int mImageResourceId) {
        this.mSongTitle = mSongTitle;
        this.mArtistName = mArtistName;
        this.mImageResourceId = mImageResourceId;
    }

    public Song(String mSongTitle, String mArtistName) {
        this.mSongTitle = mSongTitle;
        this.mArtistName = mArtistName;
    }


    public String getmSongTitle() {
        return mSongTitle;
    }

    public String getmArtistName() {
        return mArtistName;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
