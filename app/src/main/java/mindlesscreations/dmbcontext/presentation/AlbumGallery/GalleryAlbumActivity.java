package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.presentation.About.AboutActivity;
import mindlesscreations.dmbcontext.presentation.AlbumSongListing.AlbumSongListingActivity;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.AlbumGalleryComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerAlbumGalleryComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AlbumGalleryModule;

public class GalleryAlbumActivity extends BaseActivity<AlbumGalleryComponent>
        implements AlbumGalleryContract.View, AlbumAdapter.IndexOnClickListener {

    @Inject
    public AlbumGalleryContract.Presenter presenter;

    public RecyclerView recyclerView;

    // Items needed for recycler view
    private RecyclerView.LayoutManager manager;
    private AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the manager
        this.manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        this.adapter = new AlbumAdapter(this);
        // Attach adapter and manager to recyclerView
        this.recyclerView = (RecyclerView) this.findViewById(R.id.gallery_recycler_view);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.manager);

        presenter.getAlbums();
    }

    @Override
    public void onClick(View v, int index) {
        this.presenter.albumClicked(this.adapter.get(index).getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.about_item:
                this.navigateToAbout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    public void addAlbums(List<Album> albums) {
        this.adapter.addAll(albums);
    }

    @Override
    public void navigateToAlbumListing(String albumName) {
        Intent intent = new Intent(this, AlbumSongListingActivity.class);
        intent.putExtra(AlbumSongListingActivity.EXTRA_ALBUM_NAME, albumName);

        this.startActivity(intent);
    }

    private void navigateToAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected AlbumGalleryComponent buildComponent() {
        return DaggerAlbumGalleryComponent.builder()
                .appComponent(this.getApplicationComponent())
                .albumGalleryModule(new AlbumGalleryModule(this))
                .build();
    }

    @Override
    protected void doInjection() {
        this.getComponent().inject(this);
    }

}
