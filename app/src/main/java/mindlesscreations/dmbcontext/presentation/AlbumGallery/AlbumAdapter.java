package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import android.content.Context;
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
    private Context context;

    public AlbumAdapter(Context context) {
        this.albums = new ArrayList<>();
        this.context = context;
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
                .load(album.getCoverurl()).fit().centerCrop().into(holder.albumImage);

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

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        public ImageView albumImage;
        public TextView albumName;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            this.albumImage = (ImageView) itemView.findViewById(R.id.album_image);
            this.albumName = (TextView) itemView.findViewById(R.id.album_name);
        }
    }
}
