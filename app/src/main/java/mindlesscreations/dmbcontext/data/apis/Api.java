package mindlesscreations.dmbcontext.data.apis;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.entities.Album;
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

    public class ListAlbumResponse {
        public List<Album> albums;
    }
}
