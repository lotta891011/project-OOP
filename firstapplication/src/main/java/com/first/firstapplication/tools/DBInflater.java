package com.first.firstapplication.tools;

import com.first.firstapplication.model.Artist;
import com.first.firstapplication.model.Song;
import com.first.firstapplication.repositories.ArtistRepository;
import com.first.firstapplication.repositories.SongRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    public DBInflater(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    private SongRepository songRepository;
    private ArtistRepository artistRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Artist tessa = new Artist("Tessa", "Violet", "Tess");
        Song badIdeas = new Song("Bad Ideas", "alternative pop", "5922266554",
                "2019", "T∆G Music");
        tessa.getSongs().add(badIdeas);
        badIdeas.getArtists().add(tessa);
        artistRepository.save(tessa);
        songRepository.save(badIdeas);


        Artist andy = new Artist("Andy", "Black", "Andy");
        Song weDont = new Song("We don't have to dance", "rock", "98496712523",
                "2016", "Lava Music");
        andy.getSongs().add(weDont);
        weDont.getArtists().add(andy);
        artistRepository.save(andy);
        songRepository.save(weDont);


        Artist rozen = new Artist("Andrzej", "Rozen", "Rozen");
        Song nieWyjade = new Song("Nie wyjadę", "alternative pop", "08634723091",
                "2020", "Fonobo Label");
        rozen.getSongs().add(nieWyjade);
        nieWyjade.getArtists().add(rozen);
        artistRepository.save(rozen);
        songRepository.save(nieWyjade);
    }
}
