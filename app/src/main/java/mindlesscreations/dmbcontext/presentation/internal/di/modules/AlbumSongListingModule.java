package mindlesscreations.dmbcontext.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mindlesscreations.dmbcontext.domain.interactors.GetSongsOnAlbum;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.execution.UIThread;
import mindlesscreations.dmbcontext.presentation.AlbumSongListing.AlbumSongListingActivity;
import mindlesscreations.dmbcontext.presentation.AlbumSongListing.AlbumSongListingContract;
import mindlesscreations.dmbcontext.presentation.AlbumSongListing.AlbumSongListingPresenter;

@Module
public class AlbumSongListingModule {
    private AlbumSongListingActivity activity;

    public AlbumSongListingModule(AlbumSongListingActivity activity) {
        this.activity = activity;
    }

    @Provides
    public AlbumSongListingContract.Presenter providePresenter(GetSongsOnAlbum.GetSongsOnAlbumFactory factory) {
        return new AlbumSongListingPresenter(factory, this.activity);
    }

    @Provides
    public GetSongsOnAlbum.GetSongsOnAlbumFactory provideGetSongsOnAlbum(UIThread uiThread, ThreadExecutor executor,
                                                                         GetSongsOnAlbum.GetSongsOnAlbumRepo repo) {
        return new GetSongsOnAlbum.GetSongsOnAlbumFactory(executor, uiThread, repo);
    }
}
