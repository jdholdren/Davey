package mindlesscreations.dmbcontext.presentation.Search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerSearchComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.SearchComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.SearchModule;

public class SearchActivity extends BaseActivity<SearchComponent> implements SearchContract.View {

    public static final String EXTRA_SONG_ID
            = "mindlesscreations.dmbcontext.presentation.Search.extras.SONG_ID";

    @Inject
    public SearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.handleIntent(this.getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        this.handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            int songId = intent.getIntExtra(EXTRA_SONG_ID, -1);

            this.presenter.searchLyrics(songId, query);
        }
    }

    @Override
    public void displayResults(List<Performance> performances) {
        // TODO: Add to adapter the results
    }

    @Override
    protected SearchComponent buildComponent() {
        return DaggerSearchComponent.builder()
                .appComponent(this.getApplicationComponent())
                .searchModule(new SearchModule(this))
                .build();
    }

    @Override
    protected void doInjection() {
        this.getComponent().inject(this);
    }
}
