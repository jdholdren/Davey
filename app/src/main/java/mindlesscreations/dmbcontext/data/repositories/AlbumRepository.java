package mindlesscreations.dmbcontext.data.repositories;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import rx.Observable;
import rx.Subscriber;

public class AlbumRepository implements GetAlbums.GetAllAlbumsRepo {

    private AlbumApi api;

    public AlbumRepository(AlbumApi api) {
        this.api = api;
    }

    @Override
    public Observable<List<Album>> getAlbums() {
        final AlbumApi albumApi = this.api;

        Observable<List<Album>> observable = Observable.create(new Observable.OnSubscribe<List<Album>>() {
            @Override
            public void call(Subscriber<? super List<Album>> subscriber) {
                List<Album> albums = albumApi.findAll();
                subscriber.onNext(albums);
                subscriber.onCompleted();
            }
        });

        return observable;
    }

    public interface AlbumApi {
        List<Album> findAll();
    }
}