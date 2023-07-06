package com.niit.jdp;

import com.niit.jdp.exception.MusicPlayerException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.MusicPlayerService;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MusicPlayerException, SQLException {

        try {
            MusicPlayerService playerService = new MusicPlayerService();
            SongRepository songRepository = new SongRepository();
            PlaylistRepository playlistRepository = new PlaylistRepository();
            List<Song> allSongs = songRepository.displayAllSongs();
            Scanner scanner = new Scanner(System.in);

            int choice = 0;
            do {
                System.out.println();
                System.out.println("------------------\u001b[32m Welcome to The Jukebox \u001b[0m--------------------");
                System.out.println("______________________________________________________________");
                System.out.println("1. Song Player");
                System.out.println("2. Playlist");
                System.out.println("3. Exit");
                System.out.println("______________________________________________________________");
                System.out.print("\u001b[32m Enter your Choice : \u001b[0m");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: {
                        int choice2 = 0;
                        do {
                            System.out.println();
                            System.out.println("~~~~~~~~~~~~~\u001b[32m Welcome to Song Player \u001b[0m~~~~~~~~~~~~~");
                            System.out.println("__________________________________________________");
                            System.out.println("1. Play a Song from the list");
                            System.out.println("2. Search a Song by Song name");
                            System.out.println("3. Search a Song by Artist name");
                            System.out.println("4. Search a Song by Genre name");
                            System.out.println("5. Exit from song player");
                            System.out.println("__________________________________________________");
                            System.out.print("\u001b[32m Enter your choice : \u001b[0m");
                            choice2 = scanner.nextInt();
                            scanner.nextLine();

                            switch (choice2) {
                                case 1:
                                    for (Song allSong : allSongs) {
                                        System.out.println("\n" + allSong.getSongId() + "\u001b[32m. \u001b[0m"
                                                + allSong.getSongName() + "\u001b[32m  by~  \u001b[0m"
                                                + allSong.getArtist() + "\u001b[32m from~ \u001b[0m"
                                                + allSong.getAlbum() + "\u001b[32m of Genre \u001b[0m"
                                                + allSong.getGenre() + "\u001b[32m - \u001b[0m"
                                                + allSong.getDuration());
                                    }
                                    System.out.print(" Enter Song number to play that song : ");
                                    int songId = scanner.nextInt();
                                    scanner.nextLine();
                                    String url = " ";
                                    Iterator<Song> iterator = allSongs.iterator();
                                    while (iterator.hasNext()) {
                                        Song next = iterator.next();
                                        if (songId == next.getSongId())
                                            url = next.getSongUrl();
                                    }
                                    System.out.println("\u001b[32m Song will be paused after completion.\u001b[0m");
                                    playerService.play(url);
                                    System.out.println("______________________________________________________________");
                                    System.out.print(" Enter \u001b[32m 0 \u001b[0m to Go back to Menu : ");
                                    int entry1 = scanner.nextInt();
                                    scanner.nextLine();
                                    if (entry1 == 0)
                                        playerService.stop();

                                    break;

                                case 2:
                                    System.out.print(" Enter Song title you want to find : ");
                                    String songName = scanner.nextLine();
                                    Song songByName = songRepository.searchSongByTitle(songName);
                                    System.out.println(songByName);
                                    System.out.println("______________________________________________________________");
                                    System.out.print(" Do you want to play this song : \u001b[32m Y/N \u001b[0m : ");
                                    String yourChoice2 = scanner.nextLine();
                                    if (yourChoice2.equalsIgnoreCase("Y")) {
                                        System.out.println("\u001b[32m Song will be paused after completion.\u001b[0m");
                                        playerService.play(songByName.getSongUrl());
                                        System.out.println("______________________________________________________________");
                                        System.out.print("\n Enter \u001b[32m 0 \u001b[0m to Go back to Menu : ");
                                        int entry2 = scanner.nextInt();
                                        scanner.nextLine();
                                        if (entry2 == 0)
                                            playerService.stop();
                                    } else if (yourChoice2.equalsIgnoreCase("N")) {
                                        System.out.println("______________________________________________________________");
                                    }
                                    break;

                                case 3:
                                    System.out.print(" Enter Artist name to find there songs : ");
                                    String artistName = scanner.nextLine();
                                    Song songByArtistName = songRepository.searchSongByArtist(artistName);
                                    System.out.println(songByArtistName);
                                    System.out.println("______________________________________________________________");
                                    System.out.print(" Do you want to play this song : \u001b[32m Y/N \u001b[0m : ");
                                    String yourChoice3 = scanner.nextLine();
                                    if (yourChoice3.equalsIgnoreCase("Y")) {
                                        System.out.println("\u001b[32m Song will be paused after completion.\u001b[0m");
                                        playerService.play(songByArtistName.getSongUrl());
                                        System.out.println("______________________________________________________________");
                                        System.out.print("\n Enter \u001b[32m 0 \u001b[0m to Go back to Menu : ");
                                        int entry3 = scanner.nextInt();
                                        scanner.nextLine();
                                        if (entry3 == 0)
                                            playerService.stop();
                                    } else if (yourChoice3.equalsIgnoreCase("N")) {
                                        System.out.println("______________________________________________________________");
                                    }

                                    break;

                                case 4:
                                    System.out.print(" Enter Genre of song to find the song : ");
                                    String genre = scanner.nextLine();
                                    Song songByGenre = songRepository.searchSongByGenre(genre);
                                    System.out.println(songByGenre);
                                    System.out.println("______________________________________________________________");
                                    System.out.print(" Do you want to play this song : \u001b[32m Y/N \u001b[0m : ");
                                    String yourChoice4 = scanner.nextLine();
                                    if (yourChoice4.equalsIgnoreCase("Y")) {
                                        System.out.println("\u001b[32m Song will be paused after completion.\u001b[0m");
                                        playerService.play(songByGenre.getSongUrl());
                                        System.out.println("______________________________________________________________");
                                        System.out.print("\n Enter \u001b[32m 0 \u001b[0m to Go back to Menu : ");
                                        int entry4 = scanner.nextInt();
                                        scanner.nextLine();
                                        if (entry4 == 0)
                                            playerService.stop();
                                    } else if (yourChoice4.equalsIgnoreCase("N")) {
                                        System.out.println("______________________________________________________________");
                                    }
                                    break;

                                case 5:
                                    System.out.print("\u001b[32m Exiting the Application \u001b[0m : ");
                                    break;

                                default:
                                    System.err.println(" Invalid Choice !!");
                            }
                        } while (choice2 != 5);
                    }
                    break;

                    case 2: {
                        int choice3 = 0;
                        do {
                            System.out.println();
                            System.out.println("~~~~~~~~~~~~~~~\u001b[32m Welcome to Playlist \u001b[0m~~~~~~~~~~~~~~~");
                            System.out.println("___________________________________________________");
                            System.out.println("1. Create a playlist");
                            System.out.println("2. Add songs in a playlist");
                            System.out.println("3. Display the playlist and Play Song from Playlist");
                            System.out.println("4. Exit from Playlist");
                            System.out.println("___________________________________________________");

                            System.out.print("\u001b[32m Enter your Choice : \u001b[0m");
                            choice3 = scanner.nextInt();
                            scanner.nextLine();

                            switch (choice3) {
                                case 1:
                                    songRepository.displayAllSongs().forEach(System.out::println);
                                    System.out.print(" Enter a name you want to give to your playlist : ");
                                    String playlistName = scanner.nextLine();
                                    scanner.nextLine();
                                    Playlist playlist = playlistRepository.createPlaylist(playlistName);
                                    System.out.println("\u001b[32m Your playlist has been created successfully with Playlist Id \u001b[0m" + playlist.getPlaylistId() + "\u001b[32m Playlist Name \u001b[0m" + playlist.getPlaylistName());
                                    System.out.println("______________________________________________________________");
                                    break;

                                case 2:
                                    System.out.print(" Enter playlist Id to add songs to playlist : ");
                                    int playlistId = scanner.nextInt();
                                    scanner.nextLine();
                                    for (Song allSong : allSongs) {
                                        System.out.println("\n" + allSong.getSongId() + "\u001b[32m. \u001b[0m"
                                                + allSong.getSongName() + "\u001b[32m  by~  \u001b[0m"
                                                + allSong.getArtist() + "\u001b[32m from~ \u001b[0m"
                                                + allSong.getAlbum() + "\u001b[32m of Genre \u001b[0m"
                                                + allSong.getGenre() + "\u001b[32m - \u001b[0m"
                                                + allSong.getDuration());
                                    }
                                    System.out.print(" Enter the songs ids to add to the playlist separated by commas : ");
                                    String songId = scanner.nextLine();
                                    boolean songs = playlistRepository.addSongsToPlaylist(playlistId, songId);
                                    if (songs) {
                                        System.out.println("\u001b[32m Songs has been added to the playlist successfully \u001b[0m");
                                    } else {
                                        System.out.println(" Something went wrong during the process");
                                    }
                                    System.out.println("______________________________________________________________");
                                    break;

                                case 3:
                                    System.out.print(" Enter the playlist id to display the playlist : ");
                                    int playlistIdToGetSongs = scanner.nextInt();
                                    List<Song> songsFromPlaylist = playlistRepository.getSongsFromPlaylist(playlistIdToGetSongs);

                                    for (Song songs1 : songsFromPlaylist) {
                                        System.out.println(songs1);
                                    }
                                    Iterator<Song> iterator = songsFromPlaylist.iterator();
                                    Song song = null;
                                    while (iterator.hasNext()) {
                                        song = iterator.next();
                                    }
                                    System.out.println("\u001b[32m Play song from playlist \u001b[0m");
                                    System.out.print(" Enter song id to play that song : ");
                                    int songIdFromPlaylist = scanner.nextInt();
                                    scanner.nextLine();
                                    if (songIdFromPlaylist == song.getSongId())
                                        System.out.println("\u001b[32m Song will be paused after completion.\u001b[0m");
                                    playerService.play(song.getSongUrl());
                                    System.out.println("______________________________________________________________");
                                    System.out.print("\n Enter \u001b[32m 0 \u001b[0m to Go back to Menu : ");
                                    int entry4 = scanner.nextInt();
                                    scanner.nextLine();
                                    if (entry4 == 0)
                                        playerService.stop();

                                    break;

                                case 4:
                                    System.out.println("\u001b[32m Exiting from playlist \u001b[0m");
                                    break;
                                default:
                                    System.err.println(" Invalid choice !!");
                            }
                        }
                        while (choice3 != 4);
                    }
                    case 3:
                        System.out.println("\u001b[32m Exiting from Application \u001b[0m");
                        break;

                    default:
                        System.err.println(" Invalid choice !!");
                }
            }
            while (choice != 3);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        } catch (MusicPlayerException exception) {
            System.out.println(exception.toString());
        }
    }
}