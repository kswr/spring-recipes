package com.kswr.spring.recipes.springrecipes.repositories;

import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitRepository extends CrudRepository<UnitOfMeasure, Long> {
}
