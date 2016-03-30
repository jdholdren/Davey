package mindlesscreations.dmbcontext.presentation.internal.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.domain.interactors.GetPerformanceLyrics;
import mindlesscreations.dmbcontext.domain.interactors.GetSongsOnAlbum;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.execution.UIThread;
import mindlesscreations.dmbcontext.presentation.DMBCApplication;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Application app();
    ThreadExecutor executor();
    PostExecutionThread postExecThread();
    AlbumRepository repo();
    GetAlbums.GetAllAlbumsRepo albumRepo();
    AlbumRepository.AlbumApi api();
    UIThread uiThread();
    GetSongsOnAlbum.GetSongsOnAlbumRepo albumSongsRepo();
    GetPerformanceLyrics.GetPerformanceRepo studioPerformanceRepo();

    void inject(DMBCApplication app);
}
