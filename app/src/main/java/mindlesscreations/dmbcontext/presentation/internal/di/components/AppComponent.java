package mindlesscreations.dmbcontext.presentation.internal.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.execution.UIThread;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    ThreadExecutor executor();
    PostExecutionThread postExecThread();
    GetAlbums.GetAllAlbumsRepo repo();
    AlbumRepository.AlbumApi api();
    UIThread uiThread();

    void inject(Application app);
}
