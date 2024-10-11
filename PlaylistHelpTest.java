import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PlaylistHelpTest {
    private PlaylistHelp playlist;
    private SongHelp song1;
    private SongHelp song2;


    @BeforeEach
    public void setUp() {
        playlist = new PlaylistHelp("ПЛЕЙЛИСТ");
        song1 = new SongHelp("Название 1", "Артист 1");
        song2 = new SongHelp("Название 2", "Артист 2");

    }

    @Test
    public void addSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        assertEquals(2, playlist.getSongs().size());
        assertEquals(song1, playlist.getSongs().get(0));
        assertEquals(song2, playlist.getSongs().get(1));
    }

    @Test
    public void removeSong() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        assertEquals(2, playlist.getSongs().size());

        playlist.removeSong(0);
        assertEquals(1, playlist.getSongs().size());
        assertEquals(song2, playlist.getSongs().getFirst());
    }

    @Test
    public void removeSongOutOfBounds() {
        playlist.addSong(song1);
        assertEquals(1, playlist.getSongs().size());

        playlist.removeSong(1); // Удаление с недоступным индексом
        assertEquals(1, playlist.getSongs().size()); // Плейлист должен остаться неизменным
    }

    @Test
    public void getSongsEmpty() {
        assertTrue(playlist.getSongs().isEmpty(), "Список песен не должен быть пустым");
    }

    @Test
    public void getSongsAfterAdding() {
        playlist.addSong(song1);
        assertFalse(playlist.getSongs().isEmpty(), "Список песен должен содержать элементы");
        assertEquals(1, playlist.getSongs().size());
        assertEquals(song1, playlist.getSongs().getFirst());
    }

    @Test
    public void testSaveAndLoadPlaylist() {

    }


    @Test
    public void getSongByIndex() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        assertEquals(song1, playlist.getSongs().get(0));
        assertEquals(song2, playlist.getSongs().get(1));
    }

    @Test
    public void getSongByIndexOutOfBounds() {
        playlist.addSong(song1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            playlist.getSongs().get(1); // Запрос с недоступным индексом
        });
    }

    @Test
    public void removeLastSong() {
        playlist.addSong(song1);
        assertEquals(1, playlist.getSongs().size());

        playlist.removeSong(0);
        assertTrue(playlist.getSongs().isEmpty(), "Список песен должен быть пустым после удаления последней песни");
    }

    @Test
    public void addDuplicateSong() {
        playlist.addSong(song1);
        playlist.addSong(song1); // Добавляем ту же песню еще раз
        assertEquals(2, playlist.getSongs().size(), "Плейлист должен позволять дублирование песен");
    }

    @Test
    public void deletePlaylist() {
        playlist.addSong(song1);
        playlist.addSong(song2);
        assertEquals(2, playlist.getSongs().size(), "Количество песен должно быть 2 перед удалением");

        playlist.delete(); // Метод, который очищает плейлист
        assertTrue(playlist.getSongs().isEmpty(), "Список песен должен быть пустым после удаления плейлиста");
    }

}
