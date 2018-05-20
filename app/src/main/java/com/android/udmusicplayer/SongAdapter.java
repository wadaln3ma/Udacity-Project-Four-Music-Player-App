package com.android.udmusicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

class SongAdapter extends ArrayAdapter <Song> {

    public SongAdapter( Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null){
            listItem =  LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Song currentSong =  getItem(position);

        TextView artistName = listItem.findViewById(R.id.artist_name);
        artistName.setText(currentSong.getmArtistName());

        TextView songTitle = listItem.findViewById(R.id.song_name);
        songTitle.setText(currentSong.getmSongTitle());

        ImageView imageView = listItem.findViewById(R.id.music_ic);

        if (currentSong.hasImage()){
            imageView.setImageResource(currentSong.getmImageResourceId());
        }else {
            imageView.setImageResource(R.drawable.music_ic);
        }

        return listItem;
    }
}
