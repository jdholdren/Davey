package mindlesscreations.dmbcontext.domain.interactors;

import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import rx.Observable;

public class GetAlternatePerfLyrics extends GetPerformanceLyrics {
    public GetAlternatePerfLyrics(ThreadExecutor executor, PostExecutionThread postThread, GetPerformanceRepo repo, int songId) {
        super(executor, postThread, repo, songId);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.repo.getAlternatePerformances(this.songId);
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

        public GetAlternatePerfLyrics create(int songId) {
            return new GetAlternatePerfLyrics(
                    this.executor,
                    this.postExecutionThread,
                    this.repo,
                    songId
            );
        }
    }
}
