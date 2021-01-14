package com.first.firstapplication.controllers;

import com.first.firstapplication.commands.SongCommand;
import com.first.firstapplication.converters.SongCommandToSongConverter;
import com.first.firstapplication.model.Song;
import com.first.firstapplication.repositories.ArtistRepository;
import com.first.firstapplication.repositories.PublisherRepository;
import com.first.firstapplication.repositories.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongController {
    private SongRepository songRepository;
    private SongCommandToSongConverter songCommandToSongConverter;
    private PublisherRepository publisherRepository;
    private ArtistRepository artistRepository;

    public SongController(SongRepository songRepository, SongCommandToSongConverter songCommandToSongConverter, PublisherRepository publisherRepository, ArtistRepository artistRepository) {
            this.songRepository = songRepository;
            this.songCommandToSongConverter = songCommandToSongConverter;
            this.publisherRepository = publisherRepository;
            this.artistRepository = artistRepository;
    }


    @GetMapping
    @RequestMapping(value = {"/songs" , "song/list"})
    public String getSongs(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "song/list";
    }

    @GetMapping
    @RequestMapping("/song/{id}/show")
    public String getSongDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("song", songRepository.findById(id).get());
        return "song/show";
    }

    @GetMapping
    @RequestMapping("/song/{id}/delete")
    public String deleteSong(@PathVariable("id") Long id) {
        songRepository.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping
    @RequestMapping("/song/new")
    public String newSong(Model model){
        model.addAttribute("song", new SongCommand());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "song/addedit";
    }

    @PostMapping("song")
    public String saveOrUpdate(@ModelAttribute SongCommand command){
        Song detachedSong = songCommandToSongConverter.convert(command);
        Song savedSong = songRepository.save(detachedSong);

        return "redirect:/song/" + savedSong.getId() + "/show";
    }
}

