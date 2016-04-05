package mindlesscreations.dmbcontext.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Performance {
    private int id;
    private String lyrics;
    private boolean studio;
    private List<Performance> alternativePerformances;

    @SerializedName("perf_date")
    private Date peformanceDate;

    @SerializedName("song_id")
    private int songId;

    public Performance(int id,
                       String lyrics,
                       boolean studio,
                       Date peformanceDate,
                       int songId,
                       List<Performance> alternativePerformances
                   ) {
        this.id = id;
        this.lyrics = lyrics;
        this.studio = studio;
        this.peformanceDate = peformanceDate;
        this.songId = songId;
        this.alternativePerformances = alternativePerformances;
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

    public Date getPerformanceDate() {
        return peformanceDate;
    }

    public String getFormattedDate() {
        if (this.peformanceDate != null) {
            Format formatter = new SimpleDateFormat("MM/dd/yyyy");
            return formatter.format(this.peformanceDate);
        } else {
            return "";
        }
    }

    public int getSongId() {
        return songId;
    }

    public List<Performance> getAlternativePerformances() {
        return alternativePerformances;
    }
}
