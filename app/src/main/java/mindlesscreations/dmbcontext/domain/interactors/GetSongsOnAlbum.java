package mindlesscreations.dmbcontext.domain.interactors;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Song;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import rx.Observable;

public class GetSongsOnAlbum extends UseCase {

    private GetSongsOnAlbumRepo repo;
    private String albumName;

    public GetSongsOnAlbum(ThreadExecutor executor, PostExecutionThread postThread,
                           GetSongsOnAlbumRepo repo, String albumName) {
        super(executor, postThread);

        this.repo = repo;
        this.albumName = albumName;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repo.getSongsOnAlbum(this.albumName);
    }

    public interface GetSongsOnAlbumRepo {
        Observable<List<Song>> getSongsOnAlbum(String albumName);
    }

    public static class GetSongsOnAlbumFactory {
        private ThreadExecutor executor;
        private PostExecutionThread postExecutionThread;
        private GetSongsOnAlbumRepo repo;

        public GetSongsOnAlbumFactory(ThreadExecutor executor, PostExecutionThread postExecutionThread, GetSongsOnAlbumRepo repo) {
            this.executor = executor;
            this.postExecutionThread = postExecutionThread;
            this.repo = repo;
        }

        public GetSongsOnAlbum create(String albumName) {
            return new GetSongsOnAlbum(executor, postExecutionThread, repo, albumName);
        }
    }
}
