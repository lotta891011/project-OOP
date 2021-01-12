package com.first.firstapplication.repositories;

import com.first.firstapplication.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArtistRepository extends CrudRepository<Artist, Long>{
    Optional<Artist> getFirstByFirstNameAndLastName(String firstName, String lastName);
    Optional<Artist> getFirstByFirstName(String firstName);
    Optional<Artist> getFirstById(Long id);
}
