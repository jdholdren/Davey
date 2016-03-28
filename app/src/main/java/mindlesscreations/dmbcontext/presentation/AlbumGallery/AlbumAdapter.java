package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Album;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albums;
    private IndexOnClickListener listener;

    public AlbumAdapter(IndexOnClickListener listener) {
        this.albums = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);

        AlbumViewHolder viewHolder = new AlbumViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = this.albums.get(position);

        // Load the image name
        Picasso.with(holder.albumImage.getContext())
                .load(album.getCoverUrl()).fit().centerCrop().into(holder.albumImage);

        // Set the name
        holder.albumName.setText(album.getName());
    }

    @Override
    public int getItemCount() {
        return this.albums.size();
    }

    public void addAll(List<Album> albums) {
        this.albums.addAll(albums);
        this.notifyDataSetChanged();
    }

    public Album get(int index) {
        return this.albums.get(index);
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView albumImage;
        public TextView albumName;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            this.albumImage = (ImageView) itemView.findViewById(R.id.album_image);
            this.albumName = (TextView) itemView.findViewById(R.id.album_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, this.getAdapterPosition());
        }
    }

    public interface IndexOnClickListener {
        void onClick(View v, int index);
    }
}
