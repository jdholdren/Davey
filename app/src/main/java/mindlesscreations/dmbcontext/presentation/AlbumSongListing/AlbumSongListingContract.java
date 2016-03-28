package mindlesscreations.dmbcontext.presentation.AlbumSongListing;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Song;

public interface AlbumSongListingContract {
    interface Presenter {
        void getSongsOnAlbum(String albumName);
        void destroy();
    }

    interface View {
        void displaySongs(List<Song> songs);
    }
}
