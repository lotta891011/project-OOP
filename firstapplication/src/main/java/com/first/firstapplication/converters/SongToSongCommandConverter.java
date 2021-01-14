package com.first.firstapplication.converters;

import com.first.firstapplication.commands.SongCommand;
import com.first.firstapplication.model.Song;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SongToSongCommandConverter implements Converter<Song, SongCommand> {

    @Synchronized
    @Nullable
    @Override
    public SongCommand convert(Song source) {
        if (source == null) {
            return null;
        }

        final SongCommand songCommand = new SongCommand();
        songCommand.setId(source.getId());
        songCommand.setTitle(source.getTitle());
        songCommand.setGenre(source.getGenre());
        songCommand.setYear(source.getYear());
        songCommand.setIsmn(source.getIsmn());


        return songCommand;
    }
}