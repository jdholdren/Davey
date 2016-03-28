package mindlesscreations.dmbcontext.presentation.internal.di.components;

import dagger.Component;
import mindlesscreations.dmbcontext.domain.interactors.GetSongsOnAlbum;
import mindlesscreations.dmbcontext.presentation.AlbumSongListing.AlbumSongListingActivity;
import mindlesscreations.dmbcontext.presentation.AlbumSongListing.AlbumSongListingContract;
import mindlesscreations.dmbcontext.presentation.internal.di.ActivityScope;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AlbumSongListingModule;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = AlbumSongListingModule.class)
public interface AlbumSongListingComponent {

    AlbumSongListingContract.Presenter presenter();
    GetSongsOnAlbum.GetSongsOnAlbumFactory factory();

    void inject(AlbumSongListingActivity activity);
}
