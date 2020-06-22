package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.artist.ArtistCreateRequest;
import org.astelit.itunes.dto.artist.ArtistResponse;
import org.astelit.itunes.dto.artist.ArtistUpdateRequest;
import org.astelit.itunes.dto.playlist.PlaylistAddSongRequest;
import org.astelit.itunes.dto.playlist.PlaylistCreateRequest;
import org.astelit.itunes.dto.playlist.PlaylistResponse;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.service.PlaylistService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public PlaylistResponse create(@Valid @RequestBody PlaylistCreateRequest request) {
        return playlistService.create(request);
    }

    @PatchMapping
    public PlaylistResponse addSong(@Valid @RequestBody PlaylistAddSongRequest request) {
        return playlistService.add(request);
    }

    @GetMapping("{id}")
    public PlaylistResponse view(@PathVariable Long id) { return playlistService.view(id); }

    @GetMapping("/byArtist")
    public Page<PlaylistResponse> getByArtist(@RequestParam String artist) { return playlistService.byArtist(artist); }

    @GetMapping("/byAlbum")
    public Page<PlaylistResponse> getByAlbum(@RequestParam String album) { return playlistService.byAlbum(album); }

    @GetMapping("/bySong")
    public Page<PlaylistResponse> getBySong(@RequestParam String song) { return playlistService.bySong(song); }
}
