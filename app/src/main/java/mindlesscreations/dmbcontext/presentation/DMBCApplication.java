package mindlesscreations.dmbcontext.presentation;

import android.app.Application;

import mindlesscreations.dmbcontext.presentation.internal.di.components.AppComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerAppComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.AppModule;

public class DMBCApplication extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initAppComponent();
        this.component.inject(this);
    }

    private void initAppComponent() {
        this.component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return this.component;
    }
}
