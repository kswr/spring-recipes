package com.kswr.spring.recipes.springrecipes.services;

import com.kswr.spring.recipes.springrecipes.commands.RecipeCommand;
import com.kswr.spring.recipes.springrecipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);
}
