package mindlesscreations.dmbcontext.data.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {
    @GET("albums")
    Call<Api.ListAlbumResponse> getAlbums();

    @GET("songs/{albumName}")
    Call<Api.ListSongsOnAlbumResponse> getSongsOnAlbum(@Path("albumName") String albumName);

    @GET("/performances")
    Call<Api.PerformanceResponse> getStudioPerformance(@Query("song_id") int songId);

    @GET("performances")
    Call<Api.PerformanceResponse> getPerformance(@Query("perf_id") int perfId);

    @GET("search")
    Call<Api.SearchResponse> search(@Query("search") String query, @Query("song_id") int songId);
}
