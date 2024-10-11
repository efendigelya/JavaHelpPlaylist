// PlaylistHelp.java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlaylistHelp {
    private final String name;
    private final ArrayList<SongHelp> songs;

    public PlaylistHelp(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(SongHelp song) {
        songs.add(song);
    }

    public void removeSong(int index) {
        if (index >= 0 && index < songs.size()) {
            songs.remove(index);
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name + ".txt"))) {
            for (SongHelp song : songs) {
                writer.write(song.toString().trim());
                writer.newLine();
            }
            System.out.println("Плейлист сохранён в файл: " + name + ".txt");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении плейлиста: " + e.getMessage());
        }
    }
    public void delete() {
        songs.clear(); // Предполагается, что `songs` — это список песен
    }

    @Override
    public String toString() {
        return "Плейлист " + name + ": " + songs;
    }

    public String getName() {
        return name;
    }

    public ArrayList<SongHelp> getSongs() {
        return songs;
    }
}
