package mindlesscreations.dmbcontext.domain.entities;

import java.util.Date;

public class Album {
    private final String name;
    private final String coverUrl;
    private final Date releaseDate;

    public Album(String name, String coverUrl, Date releaseDate) {
        this.coverUrl = coverUrl;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
