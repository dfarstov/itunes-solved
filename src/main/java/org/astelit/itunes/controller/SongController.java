package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.song.SongCreateRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.dto.song.SongUpdateRequest;
import org.astelit.itunes.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping
    public SongResponse create(@Valid @RequestBody SongCreateRequest request) {
        return songService.create(request);
    }

    @PatchMapping
    public SongResponse update(@Valid @RequestBody SongUpdateRequest object) {
        return songService.update(object);
    }

    @GetMapping("{id}")
    public SongResponse view(@PathVariable long id) {
        return songService.view(id);
    }

    @GetMapping
    public Page<SongResponse> search(SongSearchRequest request) {
        return songService.search(request);
    }

    @GetMapping("/name")
    public Page<SongResponse> byName(@RequestParam String name) {
        return songService.byName(name);
    }

    @GetMapping("/album")
    public Page<SongResponse> byAlbum(@RequestParam String album) {
        return songService.byAlbum(album);
    }

    @GetMapping("/artist")
    public Page<SongResponse> byArtist(@RequestParam String artist) {
        return songService.byArtist(artist);
    }

    @GetMapping("/genre")
    public Page<SongResponse> byGenre(@RequestParam String genre) {
        return songService.byGenre(genre);
    }
}
