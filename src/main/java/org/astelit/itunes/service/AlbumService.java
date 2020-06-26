package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumCreateRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.album.AlbumUpdateRequest;
import org.astelit.itunes.dto.playlist.PlaylistCreateRequest;
import org.astelit.itunes.dto.playlist.PlaylistResponse;
import org.astelit.itunes.dto.playlist.PlaylistSearchRequest;
import org.astelit.itunes.dto.playlist.PlaylistUpdateRequest;
import org.astelit.itunes.entity.Album;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.User;
import org.astelit.itunes.repository.AlbumRepository;
import org.astelit.itunes.repository.ArtistRepository;
import org.astelit.itunes.repository.PlaylistRepository;
import org.astelit.itunes.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.astelit.itunes.utils.Exceptions.ALBUM_NOT_FOUND;
import static org.astelit.itunes.utils.Exceptions.ARTIST_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository repository;
    private final ArtistRepository artistRepository;

    public AlbumResponse create(AlbumCreateRequest request) {
        Artist artist = artistRepository.findById(request.getArtist()).orElseThrow(ARTIST_NOT_FOUND);

        Album album = new Album();
        album.setArtist(artist);
        album.setName(request.getName());
        album.setGenre(request.getGenre());
        album.setReleaseDate(request.getReleaseDate());

        repository.save(album);
        return new AlbumResponse(album);
    }

    public AlbumResponse update(AlbumUpdateRequest request) {
        Album album = repository.findById(request.getId()).orElseThrow(ALBUM_NOT_FOUND);
        album.setName(request.getName());
        album.setGenre(request.getGenre());
        album.setReleaseDate(request.getReleaseDate());
        repository.save(album);
        return new AlbumResponse(album);
    }

    public AlbumResponse view(long id) {
        Album album = repository.findById(id).orElseThrow(ALBUM_NOT_FOUND);
        return new AlbumResponse(album);
    }

    public Page<AlbumResponse> search(AlbumSearchRequest request) {
        return repository.search(request).map(AlbumResponse::new);
    }
}
