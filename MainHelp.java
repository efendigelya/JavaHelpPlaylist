// MainHelp.java
import java.util.ArrayList;
import java.util.Scanner;

public class MainHelp {
    private final ArrayList<PlaylistHelp> playlists;
    private final Scanner scanner;
    private int currentPlaylistIndex = -1;
    private int currentSongIndex = -1;

    public MainHelp() {
        playlists = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("                  Меню");
            System.out.println("   0 - выйти");
            System.out.println("   1 - показать список песен");
            System.out.println("   2 - создать плейлист (по имени)");
            System.out.println("   3 - включить плейлист (по номеру)");
            System.out.println("   4 - сохранить плейлист (по номеру)");
            System.out.println("   5 - удалить плейлист (по номеру)");
            System.out.println("   6 - добавить песню в плейлист (по номеру, по названию, по исполнителю)");
            System.out.println("   7 - показать весь плейлист");
            System.out.println("   8 - убрать песню из плейлиста (по номеру)");
            System.out.println("   9 - включить предыдущий трек");
            System.out.println("  10 - включить следующий трек");
            System.out.println("  11 - повторить текущий трек");

            int choice = -1;
            boolean validInput = false;

            // Перехватываем неправильные вводы
            while (!validInput) {
                System.out.println(" ");
                System.out.print("Кнопка: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    validInput = true; // Если преобразование прошло успешно, ввод действителен
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод. Введите только число.");
                }
            }

            switch (choice) {
                case 0:
                    return;
                case 1:
                    showAllSongs();
                    break;
                case 2:
                    createPlaylist();
                    break;
                case 3:
                    playPlaylist();
                    break;
                case 4:
                    savePlaylist();
                    break;
                case 5:
                    deletePlaylist();
                    break;
                case 6:
                    addSongToPlaylist();
                    break;
                case 7:
                    showPlaylist();
                    break;
                case 8:
                    removeSongFromPlaylist();
                    break;
                case 9:
                    playPreviousTrack();
                    break;
                case 10:
                    playNextTrack();
                    break;
                case 11:
                    repeatCurrentTrack();
                    break;
                default:
                    System.out.println("Неверный ввод.");
            }
        }
    }

    private void showAllSongs() { //
        if (playlists.isEmpty()) {
            System.out.println("Нет доступных плейлистов.");
            return;
        }
        System.out.println("Список всех песен среди всех плейлистов:");
        for (PlaylistHelp playlist : playlists) {
            System.out.println("Плейлист: " + playlist.getName());
            for (int i = 0; i < playlist.getSongs().size(); i++) {
                System.out.println("  " + i + ": " + playlist.getSongs().get(i));
            }
        }
    }

    private void createPlaylist() {
        System.out.println("Введи название плейлиста:");
        String name = scanner.nextLine();
        playlists.add(new PlaylistHelp(name));
        System.out.println("Плейлист \"" + name + "\" создан.");
    }

    private void playPlaylist() { //не выводит пустой плейлист
        System.out.println("Введите номер плейлиста:");
        if (scanner.hasNextInt()){
            int index = scanner.nextInt();
            scanner.nextLine();

            // Проверяем, что индекс находится в допустимых пределах
            if (index >= 0 && index < playlists.size()) {
                currentPlaylistIndex = index;

                PlaylistHelp selectedPlaylist = playlists.get(currentPlaylistIndex);

                // Проверяем, доступные ли песни в выбранном плейлисте
                if (selectedPlaylist.getSongs().isEmpty()) {
                    System.out.println("Плейлист \"" + selectedPlaylist.getName() + "\" пуст. Добавьте песни, чтобы воспроизвести.");
                    return; // Возвращаемся, чтобы не продолжать воспроизведение
                }
                currentSongIndex = 0; // Начинаем с первой песни
                System.out.println("Играет плейлист: " + playlists.get(currentPlaylistIndex));
                playCurrentSong();
            } else {
                System.out.println("Неверно введён номер плейлиста.");
            }

        }
        else {
            System.out.println("Можно ввести только индекс плейлиста.");
            scanner.nextLine();
        }

    }

    private void savePlaylist() {
        System.out.println("Введите номер плейлиста для сохранения:");
        if (scanner.hasNextInt()){
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 0 && index < playlists.size()) {
                playlists.get(index).saveToFile();
            } else {
                System.out.println("Не найден плейлист.");
            }
        }
        else {
            System.out.println("Можно ввести только индекс плейлиста.");
            scanner.nextLine();
        }
    }

    private void deletePlaylist() {
        System.out.println("Введите номер плейлиста для удаления:");
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 0 && index < playlists.size()) {
                playlists.remove(index);
                System.out.println("Плейлист удалён.");
            } else {
                System.out.println("Не найден плейлист.");
            }
        }
        else {
            System.out.println("Можно ввести только индекс плейлиста.");
            scanner.nextLine();
        }
    }

    private void addSongToPlaylist() {
        System.out.println("Введите номер плейлиста:");
        if(scanner.hasNextInt()){
            int playlistIndex = scanner.nextInt();
            scanner.nextLine();
            if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
                PlaylistHelp playlist = playlists.get(playlistIndex);
                System.out.println("Введите название песни:");
                String title = scanner.nextLine();
                System.out.println("Введите исполнителя:");
                String artist = scanner.nextLine();
                playlist.addSong(new SongHelp(title, artist));
                System.out.println("Песня добавлена.");
            } else {
                System.out.println("Не найден плейлист.");
            }
        }
        else {
            System.out.println("Можно ввести только индекс плейлиста.");
            scanner.nextLine();
        }
    }

    private void showPlaylist() {
        System.out.println("Введите номер плейлиста:");
        if(scanner.hasNextInt()){
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 0 && index < playlists.size()) {
                System.out.println(playlists.get(index));
            } else {
                System.out.println("Не найден плейлист.");
            }
        }
        else {
            System.out.println("Можно ввести только индекс плейлиста.");
            scanner.nextLine();
        }
    }

    private void removeSongFromPlaylist() {
        System.out.println("Введите номер плейлиста:");
        if (scanner.hasNextInt()) {
            int playlistIndex = scanner.nextInt();
            scanner.nextLine();
            if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
                PlaylistHelp playlist = playlists.get(playlistIndex);
                System.out.println("Введите индекс песни:");
                int songIndex = scanner.nextInt();
                playlist.removeSong(songIndex);
                System.out.println("Песня удалена.");
            } else {
                System.out.println("Не найден плейлист.");
            }
        }
        else {
            System.out.println("Можно ввести только индекс плейлиста.");
            scanner.nextLine();
        }
    }

    private void playPreviousTrack() {
        if (currentPlaylistIndex >= 0 && currentSongIndex > 0) {
            currentSongIndex--;
            playCurrentSong();
        } else {
            System.out.println("Это первый трек или плейлист не выбран.");
        }
    }

    private void playNextTrack() {
        if (currentPlaylistIndex >= 0 && currentSongIndex < playlists.get(currentPlaylistIndex).getSongs().size() - 1) {
            currentSongIndex++;
            playCurrentSong();
        } else {
            System.out.println("Это последний трек или плейлист не выбран.");
        }
    }

    private void repeatCurrentTrack() {
        if (currentPlaylistIndex >= 0) {
            playCurrentSong();
        } else {
            System.out.println("Плейлист не выбран.");
        }
    }

    private void playCurrentSong() {
        if (currentPlaylistIndex >= 0) {
            PlaylistHelp currentPlaylist = playlists.get(currentPlaylistIndex);
            if (currentSongIndex >= 0 && currentSongIndex < currentPlaylist.getSongs().size()) {
                SongHelp currentSong = currentPlaylist.getSongs().get(currentSongIndex);
                System.out.println("Воспроизведение: " + currentSong);
            } else {
                System.out.println("Неверный индекс песни.");
            }
        } else {
            System.out.println("Плейлист не выбран.");
        }
    }
}
