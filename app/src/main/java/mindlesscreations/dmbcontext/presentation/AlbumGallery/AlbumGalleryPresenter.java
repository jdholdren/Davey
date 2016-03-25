package mindlesscreations.dmbcontext.presentation.AlbumGallery;

import mindlesscreations.dmbcontext.domain.interactors.UseCase;

public class AlbumGalleryPresenter extends AlbumGalleryContract.Presenter {

    private UseCase useCase;
    private AlbumGalleryContract.View view;

    public AlbumGalleryPresenter(UseCase useCase, AlbumGalleryContract.View view) {
        this.useCase = useCase;
        this.view = view;
    }

    @Override
    public void getAlbums() {

    }

    @Override
    public void destroy() {

    }
}
