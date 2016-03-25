package mindlesscreations.dmbcontext.domain.interactors;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Album;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import rx.Observable;

public class GetAlbums extends UseCase {

    private GetAllAlbumsRepo repo;

    public GetAlbums(GetAllAlbumsRepo repo,
                     ThreadExecutor executor,
                     PostExecutionThread postThread) {
        super(executor, postThread);
        this.repo = repo;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.repo.getAlbums();
    }

    public interface GetAllAlbumsRepo {
        Observable<List<Album>> getAlbums();
    }
}
