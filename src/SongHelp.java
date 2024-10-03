// SongHelp.java
public class SongHelp {
    private String title;
    private String artist;

    public SongHelp(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "♫ " + title + " -- " + artist + "   ▶ •၊၊||၊|।|||| | \n";
    }
}
