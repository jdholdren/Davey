package mindlesscreations.dmbcontext.presentation.internal.di.components;

import dagger.Component;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.presentation.AlbumGallery.AlbumGalleryContract;
import mindlesscreations.dmbcontext.presentation.AlbumGallery.GalleryAlbumActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.ActivityScope;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AlbumGalleryModule;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {AlbumGalleryModule.class})
public interface AlbumGalleryComponent {
    GetAlbums getAlbums();
    AlbumGalleryContract.Presenter presenter();

    void inject(GalleryAlbumActivity activity);
}
