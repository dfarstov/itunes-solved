package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.album.AlbumCreateRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.album.AlbumUpdateRequest;
import org.astelit.itunes.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public AlbumResponse create(@Valid @RequestBody AlbumCreateRequest request) {
        return albumService.create(request);
    }

    @PatchMapping
    public AlbumResponse update(@Valid @RequestBody AlbumUpdateRequest object) {
        return albumService.update(object);
    }

    @GetMapping
    public Page<AlbumResponse> search(AlbumSearchRequest request) {
        return albumService.search(request);
    }

    @GetMapping("{id}")
    public AlbumResponse view(@PathVariable long id) {
        return albumService.view(id);
    }
}
