package mindlesscreations.dmbcontext.presentation.Search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.presentation.Lyrics.LyricsActivity;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerSearchComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.SearchComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.SearchModule;

public class SearchActivity extends BaseActivity<SearchComponent> implements SearchContract.View {

    public static final String EXTRA_SONG_ID
            = "mindlesscreations.dmbcontext.presentation.Search.extras.SONG_ID";

    public static final String EXTRA_SONG_NAME
            = "mindlesscreations.dmbcontext.presentation.Search.extras.SONG_NAME";

    @Inject
    public SearchContract.Presenter presenter;

    private ListView searchResults;
    private TextView emptyMessage;

    // Name of the song in case of navigation
    private String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle(R.string.search_results_label);
        this.searchResults = (ListView) this.findViewById(R.id.search_results);
        this.emptyMessage = (TextView) this.findViewById(R.id.search_empty_text);

        final Intent intent = this.getIntent();
        this.songName = intent.getStringExtra(EXTRA_SONG_NAME);

        // Attach click handler to results
        this.searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchAdapter.SearchViewHolder vh = (SearchAdapter.SearchViewHolder) view.getTag();
                SearchActivity.this.navigateToLyrics(vh.perf, intent.getStringExtra(SearchManager.QUERY));
            }
        });

        this.handleIntent(intent);
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
        this.searchResults.setAdapter(new SearchAdapter(this, R.layout.search_result, performances));
    }

    @Override
    public void displayEmpty() {
        this.emptyMessage.setVisibility(View.VISIBLE);
    }

    private void navigateToLyrics(Performance performance, String query) {
        Intent intent = new Intent(this, LyricsActivity.class);
        intent.putExtra(LyricsActivity.EXTRA_PERFORMANCE_ID, performance.getId());
        intent.putExtra(LyricsActivity.EXTRA_SONG_ID, performance.getSongId());
        intent.putExtra(LyricsActivity.EXTRA_SONG_NAME, this.songName);
        intent.putExtra(LyricsActivity.EXTRA_SEARCH_QUERY, query);

        this.startActivity(intent);
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
