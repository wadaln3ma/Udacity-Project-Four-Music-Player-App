package com.android.udmusicplayer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.SongViewHolder> {
    private List<Song> songs;
    private OnItemClickListener listener;

    SongsRecyclerAdapter(ArrayList<Song> songs){
        this.songs = songs;
    }
    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        final Song song = songs.get(position);
        holder.songTitle.setText(song.getmSongTitle());
        holder.artistName.setText(song.getmArtistName());
        final int imageResource;
        if (song.hasImage()){
            imageResource = song.getmImageResourceId();
        }else {
            imageResource = R.drawable.music_ic;
        }
        holder.imageView.setImageResource(imageResource);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(song);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView artistName;
        TextView songTitle;
        LinearLayout linearLayout;

        SongViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.music_ic);
            artistName = itemView.findViewById(R.id.artist_name);
            songTitle = itemView.findViewById(R.id.song_name);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }

    }

    void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
