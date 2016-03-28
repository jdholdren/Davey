package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import android.util.Log;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.domain.interactors.DefaultSubscriber;
import mindlesscreations.dmbcontext.domain.interactors.UseCase;

public class AlbumGalleryPresenter implements AlbumGalleryContract.Presenter {

    private UseCase useCase;
    private AlbumGalleryContract.View view;

    public AlbumGalleryPresenter(UseCase useCase, AlbumGalleryContract.View view) {
        this.useCase = useCase;
        this.view = view;
    }

    @Override
    public void getAlbums() {
        this.useCase.execute(new AlbumListSubscriber());
    }

    @Override
    public void albumClicked(String albumName) {
        this.view.navigateToAlbumListing(albumName);
    }

    @Override
    public void destroy() {
        this.useCase.unsubscribe();
    }

    private final class AlbumListSubscriber extends DefaultSubscriber<List<Album>> {
        @Override
        public void onNext(List<Album> alba) {
            AlbumGalleryPresenter.this.view.addAlbums(alba);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("hello", e.getMessage());
        }
    }
}
