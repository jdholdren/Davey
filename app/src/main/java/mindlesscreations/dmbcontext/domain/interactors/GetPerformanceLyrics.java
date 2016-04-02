package mindlesscreations.dmbcontext.domain.interactors;

import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import rx.Observable;

public class GetPerformanceLyrics extends UseCase {

    private GetPerformanceRepo repo;
    private int songId;
    private int perfId;

    public GetPerformanceLyrics(ThreadExecutor executor, PostExecutionThread postThread,
                                GetPerformanceRepo repo, int songId, int perfId) {
        super(executor, postThread);

        this.repo = repo;
        this.songId = songId;
        this.perfId = perfId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        final GetPerformanceRepo repository = this.repo;

        return repository.getPerformance(this.songId, this.perfId);
    }

    public interface GetPerformanceRepo {
        Observable<Performance> getPerformance(int songId, int perfId);
    }

    public static class Factory {
        private ThreadExecutor executor;
        private PostExecutionThread postExecutionThread;
        private GetPerformanceRepo repo;

        public Factory(ThreadExecutor executor, PostExecutionThread postExecutionThread, GetPerformanceRepo repo) {
            this.executor = executor;
            this.postExecutionThread = postExecutionThread;
            this.repo = repo;
        }

        public GetPerformanceLyrics create(int songId, int perfId) {
            return new GetPerformanceLyrics(
                    this.executor,
                    this.postExecutionThread,
                    this.repo,
                    songId,
                    perfId
            );
        }
    }
}
