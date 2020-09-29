package com.kodluyoruz.playlistapi.Domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Playlist {
    private String id;
    private String name;
    private String description;
    private String userId;
    private List<Track> tracks;
    private int trackCount;
    private int followersCount;

    public Playlist() {
    }

    public Playlist(String name, String description, String userId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.followersCount = 0;
        this.tracks = new ArrayList<Track>();
        this.trackCount = 0;
        this.userId = userId;
    }

    public Playlist(String id, String name, String description, int followersCount, List<Track> tracks, int trackCount, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.followersCount = followersCount;
        this.tracks = tracks;
        this.trackCount = trackCount;
        this.userId = userId;
    }

    public void updateDetails(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public void addTrack(Track track) {
        this.trackCount += 1;
        this.tracks.add(track);
    }

    public void removeTrack(Track track) {
        this.trackCount -= 1;
        for (Track trackListObject : this.tracks) {
            if (trackListObject.getName() == track.getName()) {
                this.tracks.remove(trackListObject);
            }
        }
    }

    public void followPlaylist() {
        this.followersCount += 1;
    }
}
