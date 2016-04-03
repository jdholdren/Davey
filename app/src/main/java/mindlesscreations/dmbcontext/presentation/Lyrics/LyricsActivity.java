package mindlesscreations.dmbcontext.presentation.Lyrics;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.presentation.Search.SearchActivity;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerLyricsComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.LyricsComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.LyricsModule;

public class LyricsActivity extends BaseActivity<LyricsComponent> implements LyricsContract.View {

    public static final String EXTRA_SONG_ID
            = "mindlesscreations.dmbcontext.presentation.Lyrics.extras.SONG_ID";

    public static final String EXTRA_SONG_NAME
            = "mindlesscreations.dmbcontext.presentation.Lyrics.extras.SONG_NAME";

    public static final String EXTRA_PERFORMANCE_ID
            = "mindlesscreations.dmbcontext.presentation.Lyrics.extras.PERF_ID";

    private TextView lyrics;
    private ListView alternateLyrics;
    private TextView lyricsVersionTitle;
    private Button showButton;

    // If the lyrics have been expanded or not
    private boolean lyricsExpanded;
    private String songName;
    private int songId;

    @Inject
    public LyricsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.lyrics = (TextView) this.findViewById(R.id.lyrics_text);
        this.alternateLyrics = (ListView) this.findViewById(R.id.alternate_dates);
        this.lyricsVersionTitle = (TextView) this.findViewById(R.id.lyrics_version_title);
        this.showButton = (Button) this.findViewById(R.id.show_more_less);

        // Grab the intent
        Intent intent = this.getIntent();
        this.songName = intent.getStringExtra(EXTRA_SONG_NAME).trim();
        this.songId = intent.getIntExtra(EXTRA_SONG_ID, -1);
        int perfId = intent.getIntExtra(EXTRA_PERFORMANCE_ID, -1);

        // Set the title
        this.getSupportActionBar().setTitle(this.songName);

        // Grab the lyrics
        this.presenter.fetchPerformance(this.songId, perfId);

        // Attach click logic to expansion
        this.showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LyricsActivity.this.toggleLyrics();
            }
        });

        this.alternateLyrics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlternatePerfAdapter.AlternatePerfHolder vh = (AlternatePerfAdapter.AlternatePerfHolder) view.getTag();
                LyricsActivity.this.presenter.alternateClicked(vh.performance);
            }
        });
    }

    @Override
    public void displayLyrics(Performance performance) {
        if (performance.getLyrics().isEmpty()) {
            this.lyrics.setText(R.string.instrumental_message);
        } else {
            this.lyrics.setText(performance.getLyrics());
        }

        // Set the title
        String title;
        if (performance.isStudio()) {
            title = this.getResources().getString(R.string.studio_lryics_label);
        } else {
            title = performance.getPerformanceDate().toString();
        }

        this.lyricsVersionTitle.setText(title);
    }

    @Override
    public void displayAlternates(List<Performance> performances) {
        ViewGroup.LayoutParams params = this.alternateLyrics.getLayoutParams();
        AlternatePerfAdapter adapter = new AlternatePerfAdapter(this, R.layout.alternate_lyric_item, performances);
        this.alternateLyrics.setAdapter(adapter);
        int sizeInDp = 50;
        int sizeInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                sizeInDp, this.getResources().getDisplayMetrics());
        params.height = adapter.getCount() * sizeInPx;
        this.alternateLyrics.setLayoutParams(params);
    }

    /**
     * Toggles the expansion of the lyrics
     */
    private void toggleLyrics() {
        // Do calculations up front
        int minInDp = 100;
        int minInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                minInDp, this.getResources().getDisplayMetrics());

        int maxSize = this.lyrics.getLineHeight() * this.lyrics.getLineCount();

        ViewGroup.LayoutParams params = this.lyrics.getLayoutParams();

        // The button text to set
        String buttonText;

        if (this.lyricsExpanded) {
            // Close the lyrics section
            params.height = minInPx;
            buttonText = this.getResources().getString(R.string.show_more);
        } else {
            // Open the lyrics section
            params.height = maxSize;
            buttonText = this.getResources().getString(R.string.show_less);
        }

        this.lyrics.setLayoutParams(params);
        this.showButton.setText(buttonText);
        this.lyricsExpanded = !this.lyricsExpanded;
    }

    @Override
    public void navigateToPerformance(Performance performance) {
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra(EXTRA_PERFORMANCE_ID, performance.getId());
        intent.putExtra(EXTRA_SONG_ID, performance.getSongId());
        intent.putExtra(EXTRA_SONG_NAME, this.songName);

        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lyrics, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public void startActivity(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            intent.putExtra(SearchActivity.EXTRA_SONG_ID, this.songId);
        }

        super.startActivity(intent);
    }

    @Override
    protected LyricsComponent buildComponent() {
        return DaggerLyricsComponent.builder()
                .appComponent(this.getApplicationComponent())
                .lyricsModule(new LyricsModule(this))
                .build();
    }

    @Override
    protected void doInjection() {
        this.getComponent().inject(this);
    }
}
