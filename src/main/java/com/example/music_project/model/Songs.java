package com.example.music_project.model;

public class Songs {
    private int id;
    private int track;
    private String title;
    private int albumId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAlbum() {
        return albumId;
    }

    public void setAlbum(int albumId) {
        this.albumId = albumId;
    }
}
