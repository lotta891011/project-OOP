package com.first.firstapplication.commands;

import com.first.firstapplication.model.Artist;
import com.first.firstapplication.model.Song;
import com.first.firstapplication.repositories.ArtistRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SongCommandToSong implements Converter<SongCommand, Song> {

    private ArtistRepository artistRepository;

    public SongCommandToSong(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Song convert(SongCommand source) {
        if (source == null) {
            return null;
        }

        final Song song = new Song();
        song.setId(source.getId());
        song.setTitle(source.getTitle());
        song.setGenre(source.getGenre());
        song.setYear(source.getYear());
        song.setIsmn(source.getIsmn());


        Optional<Artist> artist = artistRepository.findById(source.getArtistId());

        if (artist.isPresent()) {
            song.getArtists().add(artist.get());
        }

        return song;
    }
}