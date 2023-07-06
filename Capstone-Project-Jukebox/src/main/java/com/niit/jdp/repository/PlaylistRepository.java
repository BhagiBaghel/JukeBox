/*
 * Author : Bhagi Baghel
 * Date : 30.11.2022
 * Created with : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.repository;

import com.niit.jdp.exception.MusicPlayerException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {
    Connection connection;
    DatabaseService databaseService;

    public PlaylistRepository() throws SQLException, ClassNotFoundException {
        databaseService = new DatabaseService();
        // Establish connection to the database
        connection = databaseService.getConnectionToDatabase();
    }


    /**
     * This method lets the user create playlist
     *
     * @param playlistName - name of playlist user want to create
     * @return - true, if playlist created successfully, false otherwise
     */
    public Playlist createPlaylist(String playlistName) {
        Playlist playlist = new Playlist();

        String insertQuery = "INSERT INTO `music_player`.`playlist` (`playlist_name`) VALUES (?);";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, playlistName);
            int result = statement.executeUpdate();
            if (result > 0) {
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    playlist.setPlaylistId(keys.getInt(1));
                    playlist.setPlaylistName(playlistName);
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return playlist;
    }

    /**
     * This method is used to add Songs to playlist
     * @param playlistId - id of playlist
     * @param songId     - Ids of song to be added to playlist
     * @return - true, if songs are added successfully, false otherwise
     */
    public boolean addSongsToPlaylist(int playlistId, String songId) {

        String updateQuery = "UPDATE `music_player`.`playlist` SET `song_id` = ? WHERE `playlist_id` = ?;";

        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, songId);
            statement.setInt(2, playlistId);
            result = statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result > 0;
    }

    /**
     * This method is used to get the songs from playlist
     *
     * @param playlistId - id of specific playlist
     * @return - list of Song object
     */
    public List<Song> getSongsFromPlaylist(int playlistId) throws SQLException, NumberFormatException {
        List<Song> songList = new ArrayList<>();

        String selectQuery = "SELECT * FROM `music_player`.`playlist` WHERE `playlist_id` = ?;";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String songIds = resultSet.getString("song_id");
                String[] songIdArray = songIds.split(",");
                for (String songId : songIdArray) {
                    Song song = null;
                    try {
                        song = new SongRepository().searchSongById(Integer.parseInt(songId));
                    } catch (ClassNotFoundException | NumberFormatException exception) {
                        exception.printStackTrace();
                    }
                    songList.add(song);
                }
            }
        } catch (SQLException | NumberFormatException | MusicPlayerException exception) {
            exception.printStackTrace();
        }
        return songList;
    }
}