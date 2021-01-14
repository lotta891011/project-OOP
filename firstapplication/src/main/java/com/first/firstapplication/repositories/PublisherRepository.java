package com.first.firstapplication.repositories;

import com.first.firstapplication.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
