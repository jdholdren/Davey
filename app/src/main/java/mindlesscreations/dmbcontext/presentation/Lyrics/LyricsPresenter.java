package mindlesscreations.dmbcontext.presentation.Lyrics;

import android.util.Log;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.domain.interactors.DefaultSubscriber;
import mindlesscreations.dmbcontext.domain.interactors.GetAlternatePerfLyrics;
import mindlesscreations.dmbcontext.domain.interactors.GetPerformanceLyrics;
import mindlesscreations.dmbcontext.domain.interactors.UseCase;

public class LyricsPresenter implements LyricsContract.Presenter {

    private LyricsContract.View view;

    private UseCase studioPerfUseCase;
    private UseCase alternatePerfUseCase;
    private GetPerformanceLyrics.Factory getStudioLyricsFactory;
    private GetAlternatePerfLyrics.Factory getAlternateLyricsFactory;

    public LyricsPresenter(LyricsContract.View view,
                           GetPerformanceLyrics.Factory getLyricsFactory,
                           GetAlternatePerfLyrics.Factory getAlternateLyricsFactory) {
        this.view = view;
        this.getStudioLyricsFactory = getLyricsFactory;
        this.getAlternateLyricsFactory = getAlternateLyricsFactory;
    }

    @Override
    public void destroy() {
        if (this.studioPerfUseCase != null) {
            this.studioPerfUseCase.unsubscribe();
        }

        if (this.alternatePerfUseCase != null) {
            this.alternatePerfUseCase.unsubscribe();
        }
    }

    @Override
    public void loadStudioPerformance(int songId) {
        if (this.studioPerfUseCase == null) {
            this.studioPerfUseCase = this.getStudioLyricsFactory.create(songId);
        }

        this.studioPerfUseCase.execute(new LoadStudioPerformanceSubscriber());
    }

    public class LoadStudioPerformanceSubscriber extends DefaultSubscriber<Performance> {

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Performance performance) {
            LyricsPresenter.this.view.displayLyrics(performance.getLyrics());
        }
    }

    @Override
    public void loadAlternateLyrics(int songId) {
        if (this.alternatePerfUseCase == null) {
            this.alternatePerfUseCase = this.getAlternateLyricsFactory.create(songId);
        }

        this.alternatePerfUseCase.execute(new LoadAlternatePerformancesSubscriber());
    }

    public class LoadAlternatePerformancesSubscriber extends DefaultSubscriber<List<Performance>> {
        @Override
        public void onNext(List<Performance> performances) {
            Log.v("hello", performances.size() + " alternate lyrics loaded.");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("hello", e.getMessage());
        }
    }
}
