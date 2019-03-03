package com.kswr.spring.recipes.springrecipes.services;

import com.kswr.spring.recipes.springrecipes.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
