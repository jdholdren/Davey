package mindlesscreations.dmbcontext.presentation.AlbumSongListing;

import android.util.Log;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Song;
import mindlesscreations.dmbcontext.domain.interactors.DefaultSubscriber;
import mindlesscreations.dmbcontext.domain.interactors.GetSongsOnAlbum;
import mindlesscreations.dmbcontext.domain.interactors.UseCase;

public class AlbumSongListingPresenter implements AlbumSongListingContract.Presenter {

    private GetSongsOnAlbum.GetSongsOnAlbumFactory factory;
    private UseCase interactor;
    private AlbumSongListingContract.View view;

    public AlbumSongListingPresenter(GetSongsOnAlbum.GetSongsOnAlbumFactory factory, AlbumSongListingContract.View view) {
        this.factory = factory;
        this.view = view;
    }

    @Override
    public void getSongsOnAlbum(String albumName) {
        if (this.interactor == null) {
            this.interactor = this.factory.create(albumName);
        }

        interactor.execute(new ListSongsOnAlbumSubscriber());
    }

    @Override
    public void destroy() {
        if (this.interactor != null) {
            this.interactor.unsubscribe();
        }
    }

    public class ListSongsOnAlbumSubscriber extends DefaultSubscriber<List<Song>> {
        @Override
        public void onNext(List<Song> songs) {
            AlbumSongListingPresenter.this.view.displaySongs(songs);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("hello", e.getMessage());
        }
    }
}
