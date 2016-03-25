package mindlesscreations.dmbcontext.data.repositories;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;

public class AlbumRepository implements GetAlbums.GetAllAlbumsRepo {

    private AlbumApi api;

    public AlbumRepository(AlbumApi api) {
        this.api = api;
    }

    public interface AlbumApi {
        List<Album> findAll();
    }
}