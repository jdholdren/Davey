package mindlesscreations.dmbcontext.presentation.AlbumSongListing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Song;

public class SongListingAdapter
        extends RecyclerView.Adapter<SongListingAdapter.SongListingViewHolder> {

    private List<Song> songs;

    public SongListingAdapter() {
        this.songs = new ArrayList<>();
    }

    @Override
    public SongListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_listing_item, parent, false);

        return new SongListingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongListingViewHolder holder, int position) {
        holder.songTitle.setText(this.songs.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.songs.size();
    }

    public void addAll(List<Song> songs) {
        this.songs.addAll(songs);
        this.notifyDataSetChanged();
    }

    public class SongListingViewHolder extends RecyclerView.ViewHolder {
        public TextView songTitle;

        public SongListingViewHolder(View itemView) {
            super(itemView);

            this.songTitle = (TextView) itemView.findViewById(R.id.song_title);
        }
    }
}
