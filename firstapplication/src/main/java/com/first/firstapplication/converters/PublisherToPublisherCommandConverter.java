package com.first.firstapplication.converters;

import com.first.firstapplication.commands.PublisherCommand;
import com.first.firstapplication.model.Publisher;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublisherToPublisherCommandConverter implements Converter <Publisher, PublisherCommand> {


    @Synchronized
    @Nullable
    @Override
    public PublisherCommand convert(Publisher source) {
        if (source == null) {
            return null;
        }

        final PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(source.getId());
        publisherCommand.setName(source.getName());
        publisherCommand.setNip(source.getNip());
        publisherCommand.setAddress(source.getAddress());


        return publisherCommand;
    }

}