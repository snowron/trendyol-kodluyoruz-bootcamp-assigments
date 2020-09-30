package com.kodluyoruz.playlistapi.Controllers;

import com.couchbase.client.java.kv.GetResult;
import com.kodluyoruz.playlistapi.Contracts.Requests.AddTrackRequestBody;
import com.kodluyoruz.playlistapi.Contracts.Requests.CreateRequestBody;
import com.kodluyoruz.playlistapi.Contracts.Requests.UpdateRequestBody;
import com.kodluyoruz.playlistapi.Domain.Playlist;
import com.kodluyoruz.playlistapi.Domain.Track;
import com.kodluyoruz.playlistapi.Services.PlaylistService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    private PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity createPlaylist(@RequestBody CreateRequestBody createRequestBody) {
        Playlist playlist = playlistService.createPlaylist(createRequestBody);
        return ResponseEntity.created(URI.create(playlist.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findPlaylistById(@PathVariable String id) {
        Playlist playlistById = playlistService.findPlaylistById(id);
        return ResponseEntity.ok(playlistById);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity findAllByUserId(@PathVariable String userId) {
        List<Playlist> allByUserId = playlistService.findAllByUserId(userId);
        return ResponseEntity.ok(allByUserId);
    }

    @GetMapping("/search/{searchKeyword}")
    public ResponseEntity findPlaylistWithSearch(@PathVariable String searchKeyword) {
        List<Playlist> allByUserId = playlistService.searchPlaylist(searchKeyword);
        return ResponseEntity.ok(allByUserId);
    }
    @PatchMapping
    public ResponseEntity updatePlaylistDetailsById(@RequestBody UpdateRequestBody updateRequestBody) {
        playlistService.updatePlaylistDetailsById(updateRequestBody);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePlaylistById(@PathVariable String id) {
        playlistService.removePlaylistById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/track")
    public ResponseEntity addTrackToPlaylist(@RequestBody AddTrackRequestBody track, @PathVariable String id) {
        playlistService.addTrackToPlaylist(id, track);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/track")
    public ResponseEntity removeTrackToPlaylist(@RequestBody Track track, @PathVariable String id) {
        playlistService.removeTrackToPlaylist(id, track);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/follow/{userId}")
    public ResponseEntity followPlaylist(@PathVariable String id, @PathVariable String userId) {
        playlistService.followPlaylist(id, userId);
        return ResponseEntity.noContent().build();
    }
}
