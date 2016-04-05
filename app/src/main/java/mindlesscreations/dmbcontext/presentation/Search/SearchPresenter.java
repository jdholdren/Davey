package mindlesscreations.dmbcontext.presentation.Search;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.domain.interactors.DefaultSubscriber;
import mindlesscreations.dmbcontext.domain.interactors.SearchLyrics;
import mindlesscreations.dmbcontext.domain.interactors.UseCase;

public class SearchPresenter implements SearchContract.Presenter {

    private UseCase searchLyricsUseCase;
    private SearchLyrics.Factory factory;
    private SearchContract.View view;

    public SearchPresenter(SearchContract.View view, SearchLyrics.Factory factory) {
        this.factory = factory;
        this.view = view;
    }

    @Override
    public void searchLyrics(int songId, String query) {
        if (this.searchLyricsUseCase == null) {
            this.searchLyricsUseCase = this.factory.create(songId, query);
        }

        this.searchLyricsUseCase.execute(new SearchLyricsSubscriber());
    }

    public class SearchLyricsSubscriber extends DefaultSubscriber<List<Performance>> {

        @Override
        public void onNext(List<Performance> performances) {
            if (performances.size() > 0) {
                SearchPresenter.this.view.displayResults(performances);
            } else {
                SearchPresenter.this.view.displayEmpty();
            }
        }
    }

    @Override
    public void destroy() {
        if (this.searchLyricsUseCase != null) {
            this.searchLyricsUseCase.unsubscribe();
        }
    }
}
