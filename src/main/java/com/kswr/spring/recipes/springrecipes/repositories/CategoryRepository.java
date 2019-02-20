package com.kswr.spring.recipes.springrecipes.repositories;

import com.kswr.spring.recipes.springrecipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
