package mindlesscreations.dmbcontext.presentation.internal.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mindlesscreations.dmbcontext.data.apis.Api;
import mindlesscreations.dmbcontext.data.apis.RetrofitApi;
import mindlesscreations.dmbcontext.data.repositories.AlbumRepository;
import mindlesscreations.dmbcontext.domain.interactors.GetAlbums;
import mindlesscreations.dmbcontext.execution.JobExecutor;
import mindlesscreations.dmbcontext.execution.PostExecutionThread;
import mindlesscreations.dmbcontext.execution.ThreadExecutor;
import mindlesscreations.dmbcontext.execution.UIThread;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public RetrofitApi provideRetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://serene-lowlands-70032.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitApi.class);
    }

    @Provides
    @Singleton
    public AlbumRepository.AlbumApi provideAlbumApi(RetrofitApi rApi) {
        return new Api(rApi);
    }

    @Provides
    @Singleton
    public UIThread provideUIThread() {
        return new UIThread();
    }


}
