package mindlesscreations.dmbcontext.data.repositories;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.domain.entities.Song;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.domain.interactors.GetSongsOnAlbum;
import rx.Observable;
import rx.Subscriber;

public class AlbumRepository
        implements GetAlbums.GetAllAlbumsRepo, GetSongsOnAlbum.GetSongsOnAlbumRepo {

    private AlbumApi api;

    public AlbumRepository(AlbumApi api) {
        this.api = api;
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        final AlbumApi albumApi = this.api;

        return Observable.create(new Observable.OnSubscribe<List<Album>>() {
            @Override
            public void call(Subscriber<? super List<Album>> subscriber) {
                List<Album> albums = albumApi.findAll();
                subscriber.onNext(albums);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<Song>> getSongsOnAlbum(String albumName) {
        final AlbumApi albumApi = this.api;
        final String name = albumName;

        return Observable.create(new Observable.OnSubscribe<List<Song>>() {
            @Override
            public void call(Subscriber<? super List<Song>> subscriber) {
                List<Song> songs = albumApi.findAllByAlbum(name);
                subscriber.onNext(songs);
                subscriber.onCompleted();
            }
        });
    }

    public interface AlbumApi {
        List<Album> findAll();
        List<Song> findAllByAlbum(String albumName);
    }
}