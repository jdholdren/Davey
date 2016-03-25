package mindlesscreations.dmbcontext.domain.entities;

import java.util.Date;

public class Album {

    private String name;

    private String coverurl;

    private Date releaseDate;

    public Album() {}

    public Album(String name, String coverurl, Date releaseDate) {
        this.coverurl = coverurl;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
