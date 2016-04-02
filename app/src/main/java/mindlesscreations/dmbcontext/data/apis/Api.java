package mindlesscreations.dmbcontext.data.apis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.domain.entities.Song;
import retrofit2.Call;
import retrofit2.Response;

public class Api implements AlbumRepository.AlbumApi {

    private RetrofitApi retrofitApi;

    public Api(RetrofitApi retrofitApi) {
        this.retrofitApi = retrofitApi;
    }

    @Override
    public List<Album> findAll() {
        List<Album> albums = new ArrayList<>();

        Call<ListAlbumResponse> call = this.retrofitApi.getAlbums();
        try {
            Response<ListAlbumResponse> res = call.execute();

            albums.addAll(res.body().albums);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return albums;
    }

    @Override
    public List<Song> findAllByAlbum(String albumName) {
        List<Song> songs = new ArrayList<>();

        Call<ListSongsOnAlbumResponse> call = this.retrofitApi.getSongsOnAlbum(albumName);
        try {
            Response<ListSongsOnAlbumResponse> res = call.execute();

            songs.addAll(res.body().songs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songs;
    }

    @Override
    public Performance findStudioPerformance(int songId) {
        Performance performance = null;

        Call<PerformanceResponse> call = this.retrofitApi.getStudioPerformance(songId);
        try {
            Response<PerformanceResponse> res = call.execute();
            performance = res.body().performance;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return performance;
    }

    @Override
    public Performance findPerformanceById(int perfId) {
        Performance performance = null;

        Call<PerformanceResponse> call = this.retrofitApi.getPerformance(perfId);
        try {
            Response<PerformanceResponse> res = call.execute();
            performance = res.body().performance;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return performance;
    }

    public class ListAlbumResponse {
        public List<Album> albums;
    }

    public class ListSongsOnAlbumResponse {
        public List<Song> songs;
    }

    public class PerformanceResponse {
        public Performance performance;
    }
}
