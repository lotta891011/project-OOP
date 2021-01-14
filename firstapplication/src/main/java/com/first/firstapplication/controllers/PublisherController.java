package com.first.firstapplication.controllers;

import com.first.firstapplication.commands.PublisherCommand;
import com.first.firstapplication.converters.PublisherCommandToPublisherConverter;
import com.first.firstapplication.model.Publisher;
import com.first.firstapplication.repositories.ArtistRepository;
import com.first.firstapplication.repositories.PublisherRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class PublisherController {
    private PublisherRepository publisherRepository;
    private PublisherCommandToPublisherConverter publisherCommandToPublisherConverter;
    private ArtistRepository artistRepository;

    public PublisherController( PublisherCommandToPublisherConverter publisherCommandToPublisherConverter, PublisherRepository publisherRepository, ArtistRepository artistRepository) {
        this.publisherRepository = publisherRepository;
        this.publisherCommandToPublisherConverter = publisherCommandToPublisherConverter;
        this.publisherRepository = publisherRepository;
        this.artistRepository = artistRepository;
    }


    @GetMapping
    @RequestMapping(value = {"/publishers" , "publisher/list"})
    public String getPublishers(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());
        return "publishers";
    }

    @GetMapping
    @RequestMapping("/publisher/{id}/show")
    public String getPublisherDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("publisher", publisherRepository.findById(id).get());
        return "publisher/show";
    }

    @GetMapping
    @RequestMapping("/publisher/{id}/delete")
    public String deletePublisher(@PathVariable("id") Long id) {
        publisherRepository.deleteById(id);
        return "redirect:/publishers";
    }

    @GetMapping
    @RequestMapping("/publisher/new")
    public String newPublisher(Model model){
        model.addAttribute("publisher", new PublisherCommand());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
        return "publisher/addedit";
    }

    @PostMapping("publisher")
    public String saveOrUpdate(@ModelAttribute PublisherCommand command){
        Publisher detachedPublisher = publisherCommandToPublisherConverter.convert(command);
        Publisher savedSong = publisherRepository.save(detachedPublisher);

        return "redirect:/publisher/" + savedSong.getId() + "/show";
    }
}
