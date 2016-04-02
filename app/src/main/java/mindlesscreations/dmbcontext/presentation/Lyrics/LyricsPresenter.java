package mindlesscreations.dmbcontext.presentation.Lyrics;

import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.domain.interactors.DefaultSubscriber;
import mindlesscreations.dmbcontext.domain.interactors.GetPerformanceLyrics;
import mindlesscreations.dmbcontext.domain.interactors.UseCase;

public class LyricsPresenter implements LyricsContract.Presenter {

    private LyricsContract.View view;

    private UseCase getPerformance;
    private GetPerformanceLyrics.Factory getStudioLyricsFactory;

    public LyricsPresenter(LyricsContract.View view,
                           GetPerformanceLyrics.Factory getLyricsFactory) {
        this.view = view;
        this.getStudioLyricsFactory = getLyricsFactory;
    }

    @Override
    public void destroy() {
        if (this.getPerformance != null) {
            this.getPerformance.unsubscribe();
        }
    }

    @Override
    public void fetchPerformance(int songId, int perfId) {
        if (this.getPerformance == null) {
            this.getPerformance = this.getStudioLyricsFactory.create(songId, perfId);
        }

        this.getPerformance.execute(new LoadPerformanceSubscriber());
    }

    @Override
    public void alternateClicked(Performance performance) {
        this.view.navigateToPerformance(performance);
    }

    public class LoadPerformanceSubscriber extends DefaultSubscriber<Performance> {

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Performance performance) {
            LyricsPresenter.this.view.displayLyrics(performance);
            LyricsPresenter.this.view.displayAlternates(performance.getAlternativePerformances());
        }
    }
}
