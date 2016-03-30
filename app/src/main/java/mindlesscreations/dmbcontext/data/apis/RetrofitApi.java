package mindlesscreations.dmbcontext.data.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {
    @GET("albums")
    Call<Api.ListAlbumResponse> getAlbums();

    @GET("songs/{albumName}")
    Call<Api.ListSongsOnAlbumResponse> getSongsOnAlbum(@Path("albumName") String albumName);

    @GET("/songs/studioPerformance/{songId}")
    Call<Api.StudioPerformanceResponse> getStudioPerformance(@Path("songId") int songId);

    @GET("/songs/alternatePerformances/{songId}")
    Call<Api.ListAlternateLyricsResponse> getAlternatePerformances(@Path("songId") int songId);
}
