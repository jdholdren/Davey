package mindlesscreations.dmbcontext.domain.interactors;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import rx.Observable;

public class SearchLyrics extends UseCase {

    private SearchRepo repo;
    private int songId;
    private String query;

    public SearchLyrics(ThreadExecutor executor,
                        PostExecutionThread postThread,
                        SearchRepo repo,
                        int songId,
                        String query) {
        super(executor, postThread);

        this.repo = repo;
        this.query = query;
        this.songId = songId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repo.searchLyrics(this.songId, this.query);
    }

    public interface SearchRepo {
        Observable<List<Performance>> searchLyrics(int songId, String query);
    }

    public static class Factory {
        private ThreadExecutor executor;
        private PostExecutionThread postThread;
        private SearchRepo repo;

        public Factory(ThreadExecutor executor, PostExecutionThread postThread, SearchRepo repo) {
            this.executor = executor;
            this.postThread = postThread;
            this.repo = repo;
        }

        public SearchLyrics create(int songId, String query) {
            return new SearchLyrics(
                    this.executor,
                    this.postThread,
                    this.repo,
                    songId,
                    query
            );
        }
    }
}
