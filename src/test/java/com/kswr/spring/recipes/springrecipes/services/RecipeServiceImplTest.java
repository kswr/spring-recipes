package com.kswr.spring.recipes.springrecipes.services;

import com.kswr.spring.recipes.springrecipes.converters.RecipeCommandToRecipe;
import com.kswr.spring.recipes.springrecipes.converters.RecipeToRecipeCommand;
import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    // todo add tests for saving

    private RecipeServiceImpl recipeService;

    private static final Recipe recipe = Recipe.builder().id(1L).description("Nice recipe").build();

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() {
        // todo refactor to separate tests
        Optional<Recipe> optionalOfRecipe = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalOfRecipe);
        Recipe retrievedRecipe = recipeService.findById(recipe.getId());

        assertEquals(recipe.getId(), retrievedRecipe.getId());
        assertNotNull("Null recipe returned", retrievedRecipe);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() {
        // todo refactor to separate tests
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }
}