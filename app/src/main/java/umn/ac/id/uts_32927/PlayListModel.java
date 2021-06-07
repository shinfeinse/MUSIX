package umn.ac.id.uts_32927;

import android.net.Uri;

public class PlayListModel {
    String songTitle;
    String songArtist;
    String songPath;
    int songPosition;

    public PlayListModel (int songPosition, String songTitle, String songArtist, String songPath) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songPosition = songPosition;
        this.songPath = songPath;

    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public int getSongPosition() { return songPosition; }

    public String getSongPath() { return songPath; }
}
