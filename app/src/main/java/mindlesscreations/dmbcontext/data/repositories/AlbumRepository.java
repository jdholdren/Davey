package mindlesscreations.dmbcontext.data.repositories;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.domain.entities.Song;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.domain.interactors.GetPerformanceLyrics;
import mindlesscreations.dmbcontext.domain.interactors.GetSongsOnAlbum;
import mindlesscreations.dmbcontext.domain.interactors.SearchLyrics;
import rx.Observable;
import rx.Subscriber;

public class AlbumRepository
        implements GetAlbums.GetAllAlbumsRepo,
        GetSongsOnAlbum.GetSongsOnAlbumRepo,
        GetPerformanceLyrics.GetPerformanceRepo,
        SearchLyrics.SearchRepo {

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

    @Override
    public Observable<Performance> getPerformance(int songId, final int perfId) {
        final AlbumApi albumApi = this.api;
        final int songID = songId;
        final int perfID = perfId;

        return Observable.create(new Observable.OnSubscribe<Performance>() {
            @Override
            public void call(Subscriber<? super Performance> subscriber) {
                Performance performance;

                if (perfId > -1) {
                    performance = albumApi.findPerformanceById(perfID);
                } else {
                    performance = albumApi.findStudioPerformance(songID);
                }

                subscriber.onNext(performance);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<Performance>> searchLyrics(int songId, String query) {
        final AlbumApi albumApi = this.api;
        final int songID = songId;
        final String queryString = query;

        return Observable.create(new Observable.OnSubscribe<List<Performance>>() {
            @Override
            public void call(Subscriber<? super List<Performance>> subscriber) {
                List<Performance> performances = albumApi.searchPerformances(songID, queryString);

                subscriber.onNext(performances);
                subscriber.onCompleted();
            }
        });
    }

    public interface AlbumApi {
        List<Album> findAll();
        List<Song> findAllByAlbum(String albumName);
        Performance findStudioPerformance(int songId);
        Performance findPerformanceById(int perfId);
        List<Performance> searchPerformances(int songId, String query);
    }
}