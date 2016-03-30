package mindlesscreations.dmbcontext.presentation.AlbumSongListing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Song;
import mindlesscreations.dmbcontext.presentation.Lyrics.LyricsActivity;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.AlbumSongListingComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerAlbumSongListingComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AlbumSongListingModule;

public class AlbumSongListingActivity extends BaseActivity<AlbumSongListingComponent>
        implements AlbumSongListingContract.View, SongListingAdapter.IndexedOnClickListener {

    public static final String EXTRA_ALBUM_NAME
            = "mindlesscreations.dmbcontext.presentation.AlbumListing.EXTRA.AlbumName";

    @Inject
    public AlbumSongListingContract.Presenter presenter;
    private RecyclerView.LayoutManager layoutManger;
    private SongListingAdapter adapter;

    private RecyclerView songListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_song_listing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.songListing = (RecyclerView) this.findViewById(R.id.song_listing_recycler);

        // Check for the intent
        Intent intent = this.getIntent();
        String albumName = intent.getStringExtra(EXTRA_ALBUM_NAME);

        // Set the name
        getSupportActionBar().setTitle(albumName);

        // Prep the recycler
        this.adapter = new SongListingAdapter(this);
        this.layoutManger = new LinearLayoutManager(this);
        this.songListing.setLayoutManager(this.layoutManger);
        this.songListing.setAdapter(this.adapter);

        // Grab the songs from the server
        this.presenter.getSongsOnAlbum(albumName);
    }

    @Override
    public void displaySongs(List<Song> songs) {
        this.adapter.addAll(songs);
    }

    @Override
    public void navigateToLyrics(Song song) {
        Intent intent = new Intent(this, LyricsActivity.class);
        intent.putExtra(LyricsActivity.EXTRA_SONG_ID, song.getId());
        intent.putExtra(LyricsActivity.EXTRA_SONG_NAME, song.getName());

        this.startActivity(intent);
    }

    @Override
    public void onClick(View v, int index) {
        this.presenter.songClicked(this.adapter.get(index));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    protected AlbumSongListingComponent buildComponent() {
        return DaggerAlbumSongListingComponent.builder()
                .appComponent(this.getApplicationComponent())
                .albumSongListingModule(new AlbumSongListingModule(this))
                .build();
    }

    @Override
    protected void doInjection() {
        this.getComponent().inject(this);
    }
}
