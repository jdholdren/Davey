package mindlesscreations.dmbcontext.domain.entities;

import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("album_id")
    private String albumName;
    private String name;
    private int id;

    public Song(String albumName, String name, int id) {
        this.albumName = albumName;
        this.name = name;
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
