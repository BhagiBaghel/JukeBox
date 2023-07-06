/*
 * Author : Bhagi Baghel
 * Date : 30.11.2022
 * Created with : IntelliJ IDEA Community Edition
 */

package com.niit.jdp.model;

import java.util.Objects;

public class Song {
    private int songId;
    private String songName;
    private String songUrl;
    private String artist;
    private String album;
    private String genre;
    private String duration;

    public Song() {
    }

    public Song(int songId, String songName, String songUrl, String artist, String album, String genre, String duration) {
        this.songId = songId;
        this.songName = songName;
        this.songUrl = songUrl;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songId == song.songId
                && songName.equals(song.songName)
                && songUrl.equals(song.songUrl)
                && artist.equals(song.artist)
                && album.equals(song.album)
                && genre.equals(song.genre)
                && duration.equals(song.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songName, songUrl, artist, album, genre, duration);
    }
}

