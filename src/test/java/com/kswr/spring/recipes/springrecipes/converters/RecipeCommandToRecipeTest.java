package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.CategoryCommand;
import com.kswr.spring.recipes.springrecipes.commands.IngredientCommand;
import com.kswr.spring.recipes.springrecipes.commands.NotesCommand;
import com.kswr.spring.recipes.springrecipes.commands.RecipeCommand;
import com.kswr.spring.recipes.springrecipes.domain.Category;
import com.kswr.spring.recipes.springrecipes.domain.Difficulty;
import com.kswr.spring.recipes.springrecipes.domain.Ingredient;
import com.kswr.spring.recipes.springrecipes.domain.Notes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeCommandToRecipeTest {

    private RecipeCommandToRecipe converter;
    private Set<IngredientCommand> ingredients;
    private NotesCommand notes;
    private Set<CategoryCommand> categories;
    private RecipeCommand recipeCommand;
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "RecipeCommand test description";
    private static final Integer PREP_TIME = 2;
    private static final Integer COOK_TIME = 3;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "RecipeCommand test source";
    private static final String URL = "RecipeCommand test URL";
    private static final String DIRECTIONS = "RecipeCommand test directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;

    @Mock
    private IngredientCommandToIngredient ingredientConverter;

    @Mock
    private NotesCommandToNotes notesConverter;

    @Mock
    private CategoryCommandToCategory categoryConverter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        converter = new RecipeCommandToRecipe(ingredientConverter, notesConverter, categoryConverter);
        ingredients = new HashSet<>();
        ingredients.add(new IngredientCommand());
        notes = new NotesCommand();
        categories = new HashSet<>();
        categories.add(new CategoryCommand());
        recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setIngredients(ingredients);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setNotes(notes);
        recipeCommand.setCategories(categories);
        when(ingredientConverter.convert(any(IngredientCommand.class))).thenReturn(new Ingredient());
        when(notesConverter.convert(any(NotesCommand.class))).thenReturn(new Notes());
        when(categoryConverter.convert(any(CategoryCommand.class))).thenReturn(new Category());
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(recipeCommand));
    }

    @Test
    public void testConversionId() {
        assertEquals(ID, converter.convert(recipeCommand).getId());
    }

    @Test
    public void testConversionDescription() {
        assertEquals(DESCRIPTION, converter.convert(recipeCommand).getDescription());
    }

    @Test
    public void testConversionPrepTime() {
        assertEquals(PREP_TIME, converter.convert(recipeCommand).getPrepTime());
    }

    @Test
    public void testConversionCookTime() {
        assertEquals(COOK_TIME, converter.convert(recipeCommand).getCookTime());
    }

    @Test
    public void testConversionServings() {
        assertEquals(SERVINGS, converter.convert(recipeCommand).getServings());
    }

    @Test
    public void testConversionSource() {
        assertEquals(SOURCE, converter.convert(recipeCommand).getSource());
    }

    @Test
    public void testConversionUrl() {
        assertEquals(URL, converter.convert(recipeCommand).getUrl());
    }

    @Test
    public void testConversionDirections() {
        assertEquals(DIRECTIONS, converter.convert(recipeCommand).getDirections());
    }

    @Test
    public void testConversionIngredients() {
        assertEquals(1,converter.convert(recipeCommand).getIngredients().size());
        verify(ingredientConverter, times(1)).convert(any(IngredientCommand.class));
    }

    @Test
    public void testConversionWithNullIngredients() {
        recipeCommand.setIngredients(null);
        assertEquals(0,converter.convert(recipeCommand).getIngredients().size());
        verify(ingredientConverter, never()).convert(any(IngredientCommand.class));
    }

    @Test
    public void testConversionDifficulty() {
        assertEquals(DIFFICULTY, converter.convert(recipeCommand).getDifficulty());
    }

    @Test
    public void testConversionNotes() {
        assertNotNull(converter.convert(recipeCommand).getNotes());
        verify(notesConverter, times(1)).convert(any(NotesCommand.class));
    }

    @Test
    public void testConversionCategories() {
        assertEquals(1, converter.convert(recipeCommand).getCategories().size());
        verify(categoryConverter, times(1)).convert(any(CategoryCommand.class));
    }

    @Test
    public void testConversionWithNullCategories() {
        recipeCommand.setCategories(null);
        assertEquals(0, converter.convert(recipeCommand).getCategories().size());
        verify(categoryConverter, never()).convert(any(CategoryCommand.class));
    }
}