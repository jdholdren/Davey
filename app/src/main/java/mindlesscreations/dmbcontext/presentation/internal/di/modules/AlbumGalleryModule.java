package mindlesscreations.dmbcontext.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.presentation.AlbumGallery.AlbumGalleryContract;
import mindlesscreations.dmbcontext.presentation.AlbumGallery.AlbumGalleryPresenter;
import mindlesscreations.dmbcontext.presentation.AlbumGallery.GalleryAlbumActivity;

@Module
public class AlbumGalleryModule {
    private GalleryAlbumActivity activity;

    public AlbumGalleryModule(GalleryAlbumActivity activity) {
        this.activity = activity;
    }

    @Provides
    public GetAlbums provideGetAlbumsUseCase(ThreadExecutor executor, PostExecutionThread post,
                                           GetAlbums.GetAllAlbumsRepo repo) {
        return new GetAlbums(repo, executor, post);
    }

    @Provides
    public AlbumGalleryContract.Presenter providePresenter(GetAlbums useCase) {
        return new AlbumGalleryPresenter(useCase, this.activity);
    }
}
