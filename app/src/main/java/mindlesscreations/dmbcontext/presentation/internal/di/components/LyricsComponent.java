package mindlesscreations.dmbcontext.presentation.internal.di.components;

import dagger.Component;
import mindlesscreations.dmbcontext.domain.interactors.GetPerformanceLyrics;
import mindlesscreations.dmbcontext.presentation.Lyrics.LyricsActivity;
import mindlesscreations.dmbcontext.presentation.Lyrics.LyricsContract;
import mindlesscreations.dmbcontext.presentation.internal.di.ActivityScope;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.LyricsModule;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LyricsModule.class)
public interface LyricsComponent {
    LyricsContract.Presenter presenter();
    GetPerformanceLyrics.Factory factory();

    void inject(LyricsActivity activity);
}
