package mindlesscreations.dmbcontext.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Performance {
    private int id;
    private String lyrics;
    private boolean studio;

    @SerializedName("perf_date")
    private Date peformanceDate;

    @SerializedName("song_id")
    private int songId;

    public Performance(int id, String lyrics, boolean studio, Date peformanceDate, int songId) {
        this.id = id;
        this.lyrics = lyrics;
        this.studio = studio;
        this.peformanceDate = peformanceDate;
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public String getLyrics() {
        return lyrics;
    }

    public boolean isStudio() {
        return studio;
    }

    public Date getPeformanceDate() {
        return peformanceDate;
    }

    public int getSongId() {
        return songId;
    }
}
