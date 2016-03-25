package mindlesscreations.dmbcontext.presentation.internal.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mindlesscreations.dmbcontext.data.apis.Api;
import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.execution.JobExecutor;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.execution.UIThread;

@Module
public class AppModule {
    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApp() {
        return this.app;
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    public GetAlbums.GetAllAlbumsRepo provideGetAllAlbumsRepo(AlbumRepository.AlbumApi api) {
        return new AlbumRepository(api);
    }

    @Provides
    @Singleton
    public AlbumRepository.AlbumApi provideAlbumApi() {
        return new Api();
    }

    @Provides
    @Singleton
    public UIThread provideUIThread() {
        return new UIThread();
    }
}
