package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.artist.*;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.service.ArtistService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping
    public ArtistResponse create(@Valid @RequestBody ArtistCreateRequest request) {
        return artistService.create(request);
    }

    @PatchMapping
    public ArtistResponse update(@Valid @RequestBody ArtistUpdateRequest object) {
        return artistService.update(object);
    }

    @GetMapping("{id}")
    public ArtistResponse view(@PathVariable long id) {
        return artistService.view(id);
    }

    @GetMapping
    public Page<ArtistResponse> search(ArtistSearchRequest request) {
        return artistService.search(request);
    }

    @GetMapping("/byArtistName")
    public Page<ArtistResponse> getByName(@RequestParam String name) { return artistService.byName(name); }

    @GetMapping("/byGenre")
    public Page<ArtistResponse> getByGenre(@RequestParam String genre) { return artistService.byGenre(genre); }
}
