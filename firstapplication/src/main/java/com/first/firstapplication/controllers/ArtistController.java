package com.first.firstapplication.controllers;

import com.first.firstapplication.commands.ArtistCommand;
import com.first.firstapplication.converters.ArtistCommandToArtistConverter;
import com.first.firstapplication.model.Artist;
import com.first.firstapplication.repositories.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ArtistController {

    private ArtistRepository artistRepository;
    private ArtistCommandToArtistConverter artistCommandToArtistConverter;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @RequestMapping("/artists")
    public String getArtists(Model model){
        model.addAttribute("artists", artistRepository.findAll());
        return "artists";
    }

    @RequestMapping
    @GetMapping("/artist/new")
    public String newArtist(Model model){
        model.addAttribute("artist", new ArtistCommand());
        return "artist/addedit";
    }

    @PostMapping("artist")
    public String saveOrUpdate(@ModelAttribute ArtistCommand command){
        
        Optional<Artist> artistOptional = artistRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!artistOptional.isPresent()) {
            Artist detachedArtist = ArtistCommandToArtistConverter.convert(command);
            Artist savedArtist = artistRepository.save(detachedArtist);
            return "redirect:/artist/" + savedArtist.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such artist in db");
            return "redirect:/artist/" + artistOptional.get().getId() + "/show";
        }
    }
}
