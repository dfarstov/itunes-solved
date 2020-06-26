package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.album.AlbumCreateRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.album.AlbumUpdateRequest;
import org.astelit.itunes.dto.song.SongCreateRequest;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.dto.song.SongUpdateRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.Song;
import org.astelit.itunes.repository.AlbumRepository;
import org.astelit.itunes.repository.ArtistRepository;
import org.astelit.itunes.repository.SongRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import static org.astelit.itunes.utils.Exceptions.*;

@RestController
@RequiredArgsConstructor
public class SongService {
    private final SongRepository repository;
    private final AlbumRepository albumRepository;

    public SongResponse create(SongCreateRequest request) {
        Album album = albumRepository.findById(request.getAlbum()).orElseThrow(ALBUM_NOT_FOUND);

        Song song = new Song();
        song.setAlbum(album);
        song.setName(request.getName());
        song.setDuration(request.getDuration());

        repository.save(song);
        return new SongResponse(song);
    }

    public SongResponse update(SongUpdateRequest request) {
        Song song = repository.findById(request.getId()).orElseThrow(SONG_NOT_FOUND);
        song.setName(request.getName());
        repository.save(song);
        return new SongResponse(song);
    }

    public SongResponse view(long id) {
        Song song = repository.findById(id).orElseThrow(SONG_NOT_FOUND);
        return new SongResponse(song);
    }

    public Page<SongResponse> search(SongSearchRequest request) {
        return repository.search(request).map(SongResponse::new);
    }
}
