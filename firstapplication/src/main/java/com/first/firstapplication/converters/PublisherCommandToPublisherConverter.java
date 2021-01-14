package com.first.firstapplication.converters;

import com.first.firstapplication.commands.PublisherCommand;
import com.first.firstapplication.model.Publisher;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PublisherCommandToPublisherConverter implements Converter<PublisherCommand, Publisher> {



    @Synchronized
    @Nullable
    @Override
    public Publisher convert(PublisherCommand source) {
        if (source == null) {
            return null;
        }

        final Publisher publisher = new Publisher();
        publisher.setId(source.getId());
        publisher.setName(source.getName());
        publisher.setNip(source.getNip());
        publisher.setAddress(source.getAddress());

        return publisher;
    }
}