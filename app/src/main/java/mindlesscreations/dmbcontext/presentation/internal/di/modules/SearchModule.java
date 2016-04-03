package mindlesscreations.dmbcontext.presentation.internal.di.modules;

import dagger.Module;
import dagger.Provides;
import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.interactors.SearchLyrics;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.presentation.Search.SearchActivity;
import mindlesscreations.dmbcontext.presentation.Search.SearchContract;
import mindlesscreations.dmbcontext.presentation.Search.SearchPresenter;

@Module
public class SearchModule {
    private SearchActivity activity;

    public SearchModule(SearchActivity activity) {
        this.activity = activity;
    }

    @Provides
    public SearchContract.Presenter providePresenter(SearchLyrics.Factory factory) {
        return new SearchPresenter(this.activity, factory);
    }

    @Provides
    public SearchLyrics.Factory provideUseCaseFactory(ThreadExecutor executor, PostExecutionThread postExecutionThread, AlbumRepository repo) {
        return new SearchLyrics.Factory(
                executor,
                postExecutionThread,
                repo
        );
    }
}
