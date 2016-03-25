package mindlesscreations.dmbcontext.presentation.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import mindlesscreations.dmbcontext.presentation.DMBCApplication;
import mindlesscreations.dmbcontext.presentation.internal.di.components.AppComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.HasComponent;

public abstract class BaseActivity<T> extends AppCompatActivity implements HasComponent<T> {
    private T component;

    protected abstract T buildComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.component = this.buildComponent();
        this.doInjection();
        ButterKnife.bind(this);
    }

    public T getComponent() {
        return this.component;
    }

    protected AppComponent getApplicationComponent() {
        return ((DMBCApplication) getApplication()).getComponent();
    }

    protected abstract void doInjection();
}
