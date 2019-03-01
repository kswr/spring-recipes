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

    private RecipeServiceImpl recipeService;
    private Recipe recipe;
    private Optional<Recipe> optionalOfRecipe;
    private Set<Recipe> recipesData;
    private final static Long ID = 1L;

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
        recipe = Recipe.builder().id(1L).description("Nice recipe").build();
        optionalOfRecipe = Optional.of(recipe);
        recipesData = new HashSet<>();
        recipesData.add(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalOfRecipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);
    }

    @Test
    public void testGetRecipeById() {
        Recipe retrievedRecipe = recipeService.findById(recipe.getId());
        assertEquals(recipe.getId(), retrievedRecipe.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetAllRecipes() {
        assertEquals(1, recipeService.getRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {
        recipeService.deleteById(ID);
        verify(recipeRepository, times(1)).deleteById(ID);
    }
}