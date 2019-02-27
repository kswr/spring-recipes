package com.kswr.spring.recipes.springrecipes.services;

import com.kswr.spring.recipes.springrecipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe getRecipeById(Long id);
}
