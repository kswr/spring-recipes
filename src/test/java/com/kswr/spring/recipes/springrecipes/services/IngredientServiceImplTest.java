package com.kswr.spring.recipes.springrecipes.services;

import com.kswr.spring.recipes.springrecipes.commands.IngredientCommand;
import com.kswr.spring.recipes.springrecipes.converters.IngredientToIngredientCommand;
import com.kswr.spring.recipes.springrecipes.domain.Ingredient;
import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private Recipe recipe;
    private Ingredient ingredient;
    private Optional<Recipe> optionalRecipe;
    private IngredientService ingredientService;
    private IngredientCommand ingredientCommand;
    private Set<Ingredient> ingredients;
    private final static Long RECIPE_ID = 1L;
    private final static Long INGREDIENT_ID = 2L;

    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        ingredient = Ingredient.builder().id(INGREDIENT_ID).build();
        ingredients = new HashSet<>();
        ingredients.add(ingredient);
        recipe = Recipe.builder().id(RECIPE_ID).ingredients(ingredients).build();
        ingredientCommand = IngredientCommand.builder().id(INGREDIENT_ID).build();
        optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        when(ingredientToIngredientCommand.convert(any(Ingredient.class))).thenReturn(ingredientCommand);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
    }

    @Test
    public void testFindByRecipeIdAndIngredientIdNotNull() {
        assertNotNull(ingredientService.findByRecipeIdAndIngredientId(RECIPE_ID, INGREDIENT_ID));
    }

    @Test
    public void testFindByRecipeIdAndIngredientIdIngredientId() {
        assertEquals(INGREDIENT_ID, ingredientService.findByRecipeIdAndIngredientId(RECIPE_ID, INGREDIENT_ID).getId());
    }

    @Test
    public void testFindByRecipeIdAndIngredientIdRecipeRepositoryCall() {
        ingredientService.findByRecipeIdAndIngredientId(RECIPE_ID, INGREDIENT_ID);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testFindByRecipeIdAndIngredientIdIngredientToIngredientCommandCall() {
        ingredientService.findByRecipeIdAndIngredientId(RECIPE_ID, INGREDIENT_ID);
        verify(ingredientToIngredientCommand, times(1)).convert(any(Ingredient.class));
    }
}