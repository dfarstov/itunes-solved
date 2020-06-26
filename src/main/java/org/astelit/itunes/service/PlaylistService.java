package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.playlist.*;
import org.astelit.itunes.dto.song.SongResponse;
import org.astelit.itunes.dto.song.SongSearchRequest;
import org.astelit.itunes.entity.Playlist;
import org.astelit.itunes.entity.Song;
import org.astelit.itunes.entity.User;
import org.astelit.itunes.exception.NotFoundException;
import org.astelit.itunes.repository.PlaylistRepository;
import org.astelit.itunes.repository.SongRepository;
import org.astelit.itunes.repository.UserRepository;
import org.astelit.itunes.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.astelit.itunes.utils.Exceptions.*;

@RestController
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository repository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;


    public PlaylistResponse create(PlaylistCreateRequest request) {
        User author = userRepository.findById(request.getAuthor()).orElseThrow(USER_NOT_FOUND);

        Playlist playlist = new Playlist();
        playlist.setAuthor(author);
        playlist.setName(request.getName());

        repository.save(playlist);
        return new PlaylistResponse(playlist);
    }

    public PlaylistResponse update(PlaylistUpdateRequest request) {
        Playlist playlist = repository.findById(request.getId()).orElseThrow(PLAYLIST_NOT_FOUND);
        playlist.setName(request.getName());
        repository.save(playlist);
        return new PlaylistResponse(playlist);
    }

     public PlaylistResponse add(PlaylistAddSongRequest request) {
        Playlist playlist = repository.findById(request.getIdPlayList()).orElseThrow(PLAYLIST_NOT_FOUND);
        Song song = songRepository.findById(request.getIdSong()).orElseThrow(SONG_NOT_FOUND);
        playlist.getSongs().add(song);
        repository.save(playlist);
        return new PlaylistResponse(playlist);
    }

    public PlaylistResponse view(long id) {
        Playlist playlist = repository.findById(id).orElseThrow(PLAYLIST_NOT_FOUND);
        return new PlaylistResponse(playlist);
    }

    public Page<PlaylistResponse> search(PlaylistSearchRequest request) {
        return repository.search(request).map(PlaylistResponse::new);
    }
}
