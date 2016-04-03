package mindlesscreations.dmbcontext.presentation.internal.di.components;

import dagger.Component;
import mindlesscreations.dmbcontext.domain.interactors.SearchLyrics;
import mindlesscreations.dmbcontext.presentation.Search.SearchActivity;
import mindlesscreations.dmbcontext.presentation.Search.SearchContract;
import mindlesscreations.dmbcontext.presentation.internal.di.ActivityScope;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.SearchModule;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SearchModule.class)
public interface SearchComponent {
    SearchLyrics.Factory factory();
    SearchContract.Presenter presenter();

    void inject(SearchActivity activity);
}
