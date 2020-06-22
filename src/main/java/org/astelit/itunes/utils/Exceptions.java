package org.astelit.itunes.utils;

import lombok.experimental.UtilityClass;
import org.astelit.itunes.exception.NotFoundException;

import java.util.function.Supplier;

@UtilityClass
public class Exceptions {
    public static final Supplier<NotFoundException> PLAYLIST_NOT_FOUND = () -> new NotFoundException("Playlist not found");
    public static final Supplier<NotFoundException> USER_NOT_FOUND = () -> new NotFoundException("User not found");
    public static final Supplier<NotFoundException> ALBUM_NOT_FOUND = () -> new NotFoundException("Album not found");
    public static final Supplier<NotFoundException> ARTIST_NOT_FOUND = () -> new NotFoundException("Artist not found");
    public static final Supplier<NotFoundException> SONG_NOT_FOUND = () -> new NotFoundException("Song not found");
}
