package com.first.firstapplication.controllers;

import com.first.firstapplication.commands.ArtistCommand;
import com.first.firstapplication.converters.ArtistCommandToArtistConverter;
import com.first.firstapplication.converters.ArtistToArtistCommandConverter;
import com.first.firstapplication.model.Artist;
import com.first.firstapplication.repositories.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ArtistController {

    private ArtistRepository artistRepository;
    private ArtistCommandToArtistConverter artistCommandToArtistConverter;
    private ArtistToArtistCommandConverter artistToArtistCommandConverter;


    public ArtistController(ArtistRepository artistRepository, ArtistCommandToArtistConverter artistCommandToArtistConverter, ArtistToArtistCommandConverter artistToArtistCommandConverter) {
        this.artistRepository = artistRepository;
        this.artistCommandToArtistConverter=artistCommandToArtistConverter;
        this.artistToArtistCommandConverter=artistToArtistCommandConverter;
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

    @RequestMapping("/artist/{id}/show")
    public String getArtistDetails(Model model, @PathVariable("id") Long id){
        ArtistCommand editedArtist = artistToArtistCommandConverter.convert(artistRepository.findById(id).get());
        model.addAttribute("artist", editedArtist);
        return "artist/show";
    }

    @RequestMapping("/artist/{id}/delete")
    public String deleteArtist(@PathVariable("id") Long id) {
        artistRepository.deleteById(id);
        return "redirect:/artists";
    }

    @PostMapping("artist")
    public String saveOrUpdate(@ModelAttribute ArtistCommand command){

        Optional<Artist> artistOptional = artistRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!artistOptional.isPresent()) {
            Artist detachedArtist = artistCommandToArtistConverter.convert(command);
            Artist savedArtist = artistRepository.save(detachedArtist);
            return "redirect:/artist/" + savedArtist.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such artist in db");
            return "redirect:/artist/" + artistOptional.get().getId() + "/show";
        }
    }
}
