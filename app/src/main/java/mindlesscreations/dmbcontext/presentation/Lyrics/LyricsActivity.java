package mindlesscreations.dmbcontext.presentation.Lyrics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mindlesscreations.dmbcontext.R;
import mindlesscreations.dmbcontext.domain.entities.Performance;
import mindlesscreations.dmbcontext.presentation.base.BaseActivity;
import mindlesscreations.dmbcontext.presentation.internal.di.components.DaggerLyricsComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.components.LyricsComponent;
import mindlesscreations.dmbcontext.presentation.internal.di.modules.LyricsModule;

public class LyricsActivity extends BaseActivity<LyricsComponent> implements LyricsContract.View {

    public static final String EXTRA_SONG_ID
            = "mindlesscreations.dmbcontext.presentation.Lyrics.extras.SONG_ID";

    public static final String EXTRA_SONG_NAME
            = "mindlesscreations.dmbcontext.presentation.Lyrics.extras.SONG_NAME";

    private TextView lyrics;
    private ListView alternateLyrics;

    @Inject
    public LyricsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.lyrics = (TextView) this.findViewById(R.id.lyics_text);
        this.alternateLyrics = (ListView) this.findViewById(R.id.alternate_dates);

        // Set up adapter
        this.alternateLyrics.setAdapter(new AlternatePerfAdapter(this, R.layout.alternate_lyric_item, new ArrayList<Performance>()));

        // Grab the intent
        Intent intent = this.getIntent();
        String songName = intent.getStringExtra(EXTRA_SONG_NAME).trim();
        int songId = intent.getIntExtra(EXTRA_SONG_ID, -1);

        // Set the title
        this.getSupportActionBar().setTitle(songName);

        // Grab the lyrics
        this.presenter.loadStudioPerformance(songId);

        // Grab the alternate list
        this.presenter.loadAlternateLyrics(songId);
    }

    @Override
    public void displayLyrics(String lyrics) {
        if (lyrics.isEmpty()) {
            this.lyrics.setText(R.string.instrumental_message);
        } else {
            this.lyrics.setText(lyrics);
        }
    }

    @Override
    public void displayAlternates(List<Performance> performance) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
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
