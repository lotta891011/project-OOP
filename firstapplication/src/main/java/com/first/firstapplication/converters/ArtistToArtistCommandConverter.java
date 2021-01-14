package com.first.firstapplication.converters;

import com.first.firstapplication.commands.ArtistCommand;
import com.first.firstapplication.model.Artist;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ArtistToArtistCommandConverter implements Converter<Artist, ArtistCommand> {

    @Synchronized
    @Nullable
    @Override
    public ArtistCommand convert(Artist source) {
        if (source == null) {
            return null;
        }

        final ArtistCommand artistCommand = new ArtistCommand();
        artistCommand.setFirstName(source.getFirstName());
        artistCommand.setLastName(source.getLastName());
        artistCommand.setNick(source.getNick());

        return artistCommand;
    }
}