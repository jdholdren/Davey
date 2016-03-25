package mindlesscreations.dmbcontext.data.apis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.entities.Album;

public class Api implements AlbumRepository.AlbumApi {
    @Override
    public List<Album> findAll() {
        // Dummy call
        List<Album> list = new ArrayList<>();

        list.add(new Album("Stand Up", "google.com", new Date()));

        return list;
    }
}
