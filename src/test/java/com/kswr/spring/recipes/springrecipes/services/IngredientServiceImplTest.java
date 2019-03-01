package com.kswr.spring.recipes.springrecipes.services;

import com.kswr.spring.recipes.springrecipes.commands.RecipeCommand;
import com.kswr.spring.recipes.springrecipes.converters.RecipeCommandToRecipe;
import com.kswr.spring.recipes.springrecipes.converters.RecipeToRecipeCommand;
import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class IngredientServiceImplTest {

    private RecipeServiceImpl recipeServiceImpl;
    private Recipe recipe;
    private RecipeCommand recipeCommand;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
        recipe = new Recipe();
        recipeCommand = new RecipeCommand();
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
    }
}