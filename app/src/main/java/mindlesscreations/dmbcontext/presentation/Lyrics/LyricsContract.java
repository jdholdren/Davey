package mindlesscreations.dmbcontext.presentation.Lyrics;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Performance;

public interface LyricsContract {
    interface Presenter {
        void destroy();
        void loadStudioPerformance(int songId);
        void loadAlternateLyrics(int songId);
    }

    interface View {
        void displayLyrics(String lyrics);
        void displayAlternates(List<Performance> performance);
    }
}
