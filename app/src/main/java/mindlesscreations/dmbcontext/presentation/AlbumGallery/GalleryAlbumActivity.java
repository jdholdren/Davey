package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.AlbumGalleryComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerAlbumGalleryComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AlbumGalleryModule;

public class GalleryAlbumActivity extends BaseActivity<AlbumGalleryComponent> implements AlbumGalleryContract.View {

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
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    public void addAlbums(List<Album> albums) {
        this.adapter.addAll(albums);
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
