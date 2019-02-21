package com.kswr.spring.recipes.springrecipes.repositories;

import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
