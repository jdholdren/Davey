package mindlesscreations.dmbcontext.data.apis;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    @GET("albums")
    Call<Api.ListAlbumResponse> getAlbums();
}
