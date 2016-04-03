package mindlesscreations.dmbcontext.presentation.Search;

import java.util.List;

import mindlesscreations.dmbcontext.domain.entities.Performance;

public interface SearchContract {
    interface View {
        void displayResults(List<Performance> performances);
    }

    interface Presenter {
        void searchLyrics(int songId, String query);
        void destroy();
    }
}
