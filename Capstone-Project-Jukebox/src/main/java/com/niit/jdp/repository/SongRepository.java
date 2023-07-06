/*
 * Author : Bhagi Baghel
 * Date : 30.11.2022
 * Created with : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.MusicPlayerException;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {

    Connection connection;
    DatabaseService databaseService;

    public SongRepository() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        // Establish connection to the database
        connection = databaseService.getConnectionToDatabase();
    }

    /**
     * This method is used to display all the records from `songs` table inside `music_player` database.
     *
     * @return - List of Song object
     */
    public List<Song> displayAllSongs() {
        //1. Create an object of ArrayList
        List<Song> songList = new ArrayList<>();

        //2. Write a query to select all records from the `songs` table
        String selectQuery = "SELECT * FROM `music_player`.`songs`;";

        //3. Create a Statement object to send it to the database
        // ** createStatement is used for non-parameterised queries and compiles everytime it executes.
        try (Statement statement = connection.createStatement()) {

            //4. Execute the query and get the result set as return
            // DQL commands returns result set
            ResultSet resultSet = statement.executeQuery(selectQuery);
            //5. Iterate over the resultSet and get the values from the resultSet
            while (resultSet.next()) {
                //6. Get all the values from the ResultSet and store them inside variables.
                int songId = resultSet.getInt("song_id");
                String songName = resultSet.getString("song_name");
                String songUrl = resultSet.getString("song_url");
                String artist = resultSet.getString("artist");
                String album = resultSet.getString("album");
                String genre = resultSet.getString("genre");
                String duration = resultSet.getString("duration");

                //7. Create a Song object using the values from the ResultSet
                Song song = new Song(songId, songName, songUrl, artist, album, genre, duration);

                //8. Add the Song object to the songs list
                songList.add(song);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return songList;
    }


    /**
     * This method is used to Search a Song by id from database
     *
     * @param id - id of the song to be searched
     * @return - list of songs
     */
    public Song searchSongById(int id) throws MusicPlayerException {
        if (id == 0) {
            throw new MusicPlayerException("id can't be null.");
        }
        Song songListById = new Song();

        String selectQuery = "SELECT * FROM `music_player`.`songs` where `song_id` = ?;";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                songListById.setSongId(resultSet.getInt("song_id"));
                songListById.setSongName(resultSet.getString("song_name"));
                songListById.setSongUrl(resultSet.getString("song_url"));
                songListById.setArtist(resultSet.getString("artist"));
                songListById.setAlbum(resultSet.getString("album"));
                songListById.setGenre(resultSet.getString("genre"));
                songListById.setDuration(resultSet.getString("duration"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return songListById;
    }


    /**
     * This method is used to Search a Song by name from database
     *
     * @param name - name of the song to be searched
     * @return - list of songs
     */
    public Song searchSongByTitle(String name) throws MusicPlayerException, SQLException {
        if (name == null) {
            throw new MusicPlayerException("Song not found.");
        }
        String selectQuery = "SELECT * FROM `music_player`.`songs` where `song_name` = ?;";

        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        Song song = null;
        if (resultSet.next()) {
            int songId = resultSet.getInt("song_id");
            String songName = resultSet.getString("song_name");
            String songUrl = resultSet.getString("song_url");
            String artist = resultSet.getString("artist");
            String album = resultSet.getString("album");
            String genre = resultSet.getString("genre");
            String duration = resultSet.getString("duration");

            song = new Song(songId, songName, songUrl, artist, album, genre, duration);
        }
        return song;
    }


    /**
     * This method is used to Search a Song by Artist from database
     *
     * @param artistName - name of Artist to search songs for
     * @return - list of songs
     */
    public Song searchSongByArtist(String artistName) throws MusicPlayerException, SQLException {
        if (artistName == null) {
            throw new MusicPlayerException("Artist not found.");
        }
        String selectQuery = "SELECT * FROM `music_player`.`songs` where `artist` = ?;";

        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, artistName);
        ResultSet resultSet = statement.executeQuery();

        Song song = null;
        while (resultSet.next()) {
            int songId = resultSet.getInt("song_id");
            String songName = resultSet.getString("song_name");
            String songUrl = resultSet.getString("song_url");
            String artist = resultSet.getString("artist");
            String album = resultSet.getString("album");
            String genre = resultSet.getString("genre");
            String duration = resultSet.getString("duration");

            song = new Song(songId, songName, songUrl, artist, album, genre, duration);
        }
        return song;
    }


    /**
     * This method is used to Search a Song by Genre from database
     *
     * @param genreName - name of Genre to search songs for
     * @return - list of songs
     */
    public Song searchSongByGenre(String genreName) throws MusicPlayerException, SQLException {
        if (genreName == null) {
            throw new MusicPlayerException("Genre not found.");
        }
        String selectQuery = "SELECT * FROM `music_player`.`songs` where `genre` = ?;";

        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, genreName);
        ResultSet resultSet = statement.executeQuery();

        Song song = null;
        while (resultSet.next()) {
            int songId = resultSet.getInt("song_id");
            String songName = resultSet.getString("song_name");
            String songUrl = resultSet.getString("song_url");
            String artist = resultSet.getString("artist");
            String album = resultSet.getString("album");
            String genre = resultSet.getString("genre");
            String duration = resultSet.getString("duration");

            song = new Song(songId, songName, songUrl, artist, album, genre, duration);
        }
        return song;
    }


    /**
     * This method is used to Search a Song by Album from database
     *
     * @param albumName - name of Album to search songs for
     * @return - list of songs
     */
    public Song searchSongByAlbum(String albumName) throws MusicPlayerException, SQLException {
        if (albumName == null) {
            throw new MusicPlayerException("Album not found.");
        }
        String selectQuery = "SELECT * FROM `music_player`.`songs` where `artist` = ?;";

        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setString(1, albumName);
        ResultSet resultSet = statement.executeQuery();

        Song song = null;
        while (resultSet.next()) {
            int songId = resultSet.getInt("song_id");
            String songName = resultSet.getString("song_name");
            String songUrl = resultSet.getString("song_url");
            String artist = resultSet.getString("artist");
            String album = resultSet.getString("album");
            String genre = resultSet.getString("genre");
            String duration = resultSet.getString("duration");

            song = new Song(songId, songName, songUrl, artist, album, genre, duration);
        }
        return song;
    }

}
