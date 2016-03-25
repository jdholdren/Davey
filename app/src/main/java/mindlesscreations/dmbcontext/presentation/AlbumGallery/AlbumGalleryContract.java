package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.presentation.base.BasePresenter;

public interface AlbumGalleryContract {
    abstract class Presenter implements BasePresenter {
        public abstract void getAlbums();
    }

    interface View {
        void addAlbums(List<Album> albums);
    }
}
