package com.kswr.spring.recipes.springrecipes.repositories;

import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
