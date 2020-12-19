package com.first.firstapplication.repositories;

import com.first.firstapplication.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long>{
}
