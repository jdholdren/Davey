package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.AlbumGalleryComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerAlbumGalleryComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AlbumGalleryModule;

public class GalleryAlbumActivity extends BaseActivity<AlbumGalleryComponent> implements AlbumGalleryContract.View {

    @Inject
    public AlbumGalleryContract.Presenter presenter;

    @Bind(R.id.gallery_recycler_view)
    public RecyclerView recyclerView;

    // Items needed for recycler view
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Album> list = new ArrayList<>();
        list.add(new Album("Stand Up", "http://davematthewsband.com/wp-content/uploads/albums/DMDD96-292x292.jpg", new Date()));
        // Create the manager
        this.manager = new LinearLayoutManager(this);
        // Attach adapter and manager to recyclerView
        this.recyclerView = (RecyclerView) this.findViewById(R.id.gallery_recycler_view);
        this.recyclerView.setAdapter(new AlbumAdapter(list, this));
        this.recyclerView.setLayoutManager(this.manager);

        presenter.getAlbums();
    }

    @Override
    public void addAlbums() {
        // TODO: Add albums to the adapter
    }

    @Override
    protected AlbumGalleryComponent buildComponent() {
        return DaggerAlbumGalleryComponent.builder()
                .albumGalleryModule(new AlbumGalleryModule(this))
                .build();
    }

    @Override
    protected void doInjection() {
        this.getComponent().inject(this);
    }

}
