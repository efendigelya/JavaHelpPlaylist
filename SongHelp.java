// SongHelp.java
public class SongHelp {
    private final String title;
    private final String artist;

    public SongHelp(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "♫ " + title + " -- " + artist;
    }
}
