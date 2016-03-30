package mindlesscreations.dmbcontext.domain.interactors;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import rx.Observable;

public class GetPerformanceLyrics extends UseCase {

    protected GetPerformanceRepo repo;
    protected int songId;

    public GetPerformanceLyrics(ThreadExecutor executor, PostExecutionThread postThread,
                                GetPerformanceRepo repo, int songId) {
        super(executor, postThread);

        this.repo = repo;
        this.songId = songId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        final GetPerformanceRepo repository = this.repo;

        return repository.getStudioPerformance(this.songId);
    }

    public interface GetPerformanceRepo {
        Observable<Performance> getStudioPerformance(int songId);
        Observable<List<Performance>> getAlternatePerformances(int songId);
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

        public GetPerformanceLyrics create(int songId) {
            return new GetPerformanceLyrics(
                    this.executor,
                    this.postExecutionThread,
                    this.repo,
                    songId
            );
        }
    }
}
