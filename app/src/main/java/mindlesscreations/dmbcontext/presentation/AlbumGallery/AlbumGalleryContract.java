package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;

public interface AlbumGalleryContract {
    interface Presenter {
        void getAlbums();
        void albumClicked(String albumName);
        void destroy();
    }

    interface View {
        void addAlbums(List<Album> albums);
        void navigateToAlbumListing(String albumName);
    }
}
