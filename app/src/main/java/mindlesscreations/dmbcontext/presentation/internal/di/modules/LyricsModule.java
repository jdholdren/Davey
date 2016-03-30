package mindlesscreations.dmbcontext.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import mindlesscreations.dmbcontext.domain.interactors.GetAlternatePerfLyrics;
import mindlesscreations.dmbcontext.domain.interactors.GetPerformanceLyrics;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.presentation.Lyrics.LyricsContract;
import mindlesscreations.dmbcontext.presentation.Lyrics.LyricsPresenter;
import mindlesscreations.dmbcontext.presentation.Lyrics.LyricsActivity;

@Module
public class LyricsModule {
    private LyricsActivity activity;

    public LyricsModule(LyricsActivity activity) {
        this.activity = activity;
    }

    @Provides
    public LyricsContract.Presenter providePresenter(
            GetPerformanceLyrics.Factory studioFactory,
            GetAlternatePerfLyrics.Factory altFactory) {
        return new LyricsPresenter(this.activity, studioFactory, altFactory);
    }

    @Provides
    public GetPerformanceLyrics.Factory provideStudioFactory(
            ThreadExecutor executor,
            PostExecutionThread postExecutionThread,
            GetPerformanceLyrics.GetPerformanceRepo repo) {
        return new GetPerformanceLyrics.Factory(executor, postExecutionThread, repo);
    }

    @Provides
    public GetAlternatePerfLyrics.Factory provideAlternateFactory(
            ThreadExecutor executor,
            PostExecutionThread postExecutionThread,
            GetPerformanceLyrics.GetPerformanceRepo repo) {
        return new GetAlternatePerfLyrics.Factory(executor, postExecutionThread, repo);
    }
}
