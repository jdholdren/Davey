package mindlesscreations.dmbcontext.presentation.Lyrics;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Performance;

public interface LyricsContract {
    interface Presenter {
        void destroy();
        void fetchPerformance(int songId, int perfId);
        void alternateClicked(Performance performance);
    }

    interface View {
        void displayLyrics(Performance performace);
        void displayAlternates(List<Performance> performance);
        void navigateToPerformance(Performance performance);
    }
}
