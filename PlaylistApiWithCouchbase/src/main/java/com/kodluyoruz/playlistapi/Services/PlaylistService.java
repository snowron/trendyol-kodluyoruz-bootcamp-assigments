package com.kodluyoruz.playlistapi.Services;

import com.kodluyoruz.playlistapi.Contracts.Requests.AddTrackRequestBody;
import com.kodluyoruz.playlistapi.Contracts.Requests.CreateRequestBody;
import com.kodluyoruz.playlistapi.Contracts.Requests.UpdateRequestBody;
import com.kodluyoruz.playlistapi.Domain.Playlist;
import com.kodluyoruz.playlistapi.Domain.Track;
import com.kodluyoruz.playlistapi.Repositories.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createPlaylist(CreateRequestBody createRequestBody) {
        Playlist playlist = new Playlist(createRequestBody.getName(),
                createRequestBody.getDescription(), createRequestBody.getUserId());
        playlistRepository.insert(playlist);
        return playlist;
    }

    public void updatePlaylistDetailsById(UpdateRequestBody updateRequestBody) {
        Playlist playlist = playlistRepository.findById(updateRequestBody.getId());
        playlist.updateDetails(updateRequestBody.getName(), updateRequestBody.getDescription());
        playlistRepository.update(playlist);
    }

    public Playlist findPlaylistById(String id) {
        return playlistRepository.findById(id);

    }

    public List<Playlist> findAllByUserId(String userId) {
        return playlistRepository.findAllByUserId(userId);
    }

    public void removePlaylistById(String id) {
        playlistRepository.remove(id);
    }

    public void addTrackToPlaylist(String id, AddTrackRequestBody track) {
        Track trackObject = new Track(track.getName(),track.getLength(),track.getArtist());
        Playlist playlist = playlistRepository.findById(id);
        playlist.addTrack(trackObject);
        playlistRepository.update(playlist);
    }

    public void removeTrackToPlaylist(String id, Track track) {
        Playlist playlist = playlistRepository.findById(id);
        playlist.removeTrack(track);
        playlistRepository.update(playlist);
    }

    public void followPlaylist(String id, String userId) {
        Playlist playlist = playlistRepository.findById(id);
        playlist.followPlaylist();
        playlistRepository.update(playlist);
    }
}
