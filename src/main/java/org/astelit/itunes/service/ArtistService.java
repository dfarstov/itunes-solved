package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.album.AlbumResponse;
import org.astelit.itunes.dto.album.AlbumSearchRequest;
import org.astelit.itunes.dto.artist.*;
import org.astelit.itunes.entity.Artist;
import org.astelit.itunes.exception.BadRequestException;
import org.astelit.itunes.repository.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.astelit.itunes.utils.Exceptions.ARTIST_NOT_FOUND;
import static org.astelit.itunes.utils.Exceptions.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository repository;

    public ArtistResponse create(ArtistCreateRequest request) {
        if (repository.existsByName(request.getName()))
            throw new BadRequestException("Artist name " + request.getName() + " already taken");

        Artist artist = new Artist();
        artist.setName(request.getName());

        repository.save(artist);
        return new ArtistResponse(artist);
    }

    public ArtistResponse update(ArtistUpdateRequest request) {
        if (repository.existsByNameAndIdNot(request.getName(), request.getId()))
            throw new BadRequestException("Artist name " + request.getName() + " already taken");

        Artist artist = repository.findById(request.getId()).orElseThrow(ARTIST_NOT_FOUND);
        artist.setName(request.getName());

        repository.save(artist);
        return new ArtistResponse(artist);
    }

    public ArtistResponse view(long id) {
        Artist artist = repository.findById(id).orElseThrow(ARTIST_NOT_FOUND);
        return new ArtistResponse(artist);
    }

    public Page<ArtistResponse> search(SearchRequest request) {
        return repository.findByNameIsLikeOrderByNameAsc(request.getQuery(), request.pageable())
                .map(ArtistResponse::new);
    }

    //CUSTOM
    public Page<ArtistResponse> byName(String name) {
        ArtistSearchRequest request = new ArtistSearchRequest();
        request.setName(name);
        return repository.findByName(request).map(ArtistResponse::new);
    }

    public Page<ArtistResponse> byGenre(String genre) {
        ArtistSearchRequest request = new ArtistSearchRequest();
        request.setGenre(genre);
        return repository.findByGenre(request).map(ArtistResponse::new);
    }
}
