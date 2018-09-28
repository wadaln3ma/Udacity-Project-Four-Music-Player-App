package com.android.udmusicplayer;

public class Song {

    private String mSongTitle;
    private String mArtistName;
    private int mTrack;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Song(String mSongTitle, String mArtistName, int mImageResourceId, int track) {
        this.mSongTitle = mSongTitle;
        this.mArtistName = mArtistName;
        this.mImageResourceId = mImageResourceId;
        this.mTrack = track;
    }

    public Song(String mSongTitle, String mArtistName, int track) {
        this.mSongTitle = mSongTitle;
        this.mArtistName = mArtistName;
        this.mTrack = track;
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

    public int getmTrack() {
        return mTrack;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
