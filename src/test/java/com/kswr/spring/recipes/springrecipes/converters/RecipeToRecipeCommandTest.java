package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.CategoryCommand;
import com.kswr.spring.recipes.springrecipes.commands.IngredientCommand;
import com.kswr.spring.recipes.springrecipes.commands.NotesCommand;
import com.kswr.spring.recipes.springrecipes.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeToRecipeCommandTest {

    private RecipeToRecipeCommand converter;
    private Set<Ingredient> ingredients;
    private Notes notes;
    private Set<Category> categories;
    private Recipe recipe;
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Recipe test description";
    private static final Integer PREP_TIME = 2;
    private static final Integer COOK_TIME = 3;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "Recipe test source";
    private static final String URL = "Recipe test URL";
    private static final String DIRECTIONS = "Recipe test directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;

    @Mock
    private IngredientToIngredientCommand ingredientConverter;

    @Mock
    private NotesToNotesCommand notesConverter;

    @Mock
    private CategoryToCategoryCommand categoryConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        converter = new RecipeToRecipeCommand(ingredientConverter, notesConverter, categoryConverter);
        ingredients = new HashSet<>();
        ingredients.add(new Ingredient());
        notes = new Notes();
        categories = new HashSet<>();
        categories.add(new Category());
        recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setIngredients(ingredients);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setNotes(notes);
        recipe.setCategories(categories);
        when(ingredientConverter.convert(any(Ingredient.class))).thenReturn(new IngredientCommand());
        when(notesConverter.convert(any(Notes.class))).thenReturn(new NotesCommand());
        when(categoryConverter.convert(any(Category.class))).thenReturn(new CategoryCommand());
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(recipe));
    }

    @Test
    public void testConversionId() {
        assertEquals(ID, converter.convert(recipe).getId());
    }

    @Test
    public void testConversionDescription() {
        assertEquals(DESCRIPTION, converter.convert(recipe).getDescription());
    }

    @Test
    public void testConversionPrepTime() {
        assertEquals(PREP_TIME, converter.convert(recipe).getPrepTime());
    }

    @Test
    public void testConversionCookTime() {
        assertEquals(COOK_TIME, converter.convert(recipe).getCookTime());
    }

    @Test
    public void testConversionServings() {
        assertEquals(SERVINGS, converter.convert(recipe).getServings());
    }

    @Test
    public void testConversionSource() {
        assertEquals(SOURCE, converter.convert(recipe).getSource());
    }

    @Test
    public void testConversionUrl() {
        assertEquals(URL, converter.convert(recipe).getUrl());
    }

    @Test
    public void testConversionDirections() {
        assertEquals(DIRECTIONS, converter.convert(recipe).getDirections());
    }

    @Test
    public void testConversionIngredients() {
        assertEquals(1,converter.convert(recipe).getIngredients().size());
        verify(ingredientConverter, times(1)).convert(any(Ingredient.class));
    }

    @Test
    public void testConversionWithNullIngredients() {
        recipe.setIngredients(null);
        assertEquals(0,converter.convert(recipe).getIngredients().size());
        verify(ingredientConverter, never()).convert(any(Ingredient.class));
    }

    @Test
    public void testConversionDifficulty() {
        assertEquals(DIFFICULTY, converter.convert(recipe).getDifficulty());
    }

    @Test
    public void testConversionNotes() {
        assertNotNull(converter.convert(recipe).getNotes());
        verify(notesConverter, times(1)).convert(any(Notes.class));
    }

    @Test
    public void testConversionCategories() {
        assertEquals(1, converter.convert(recipe).getCategories().size());
        verify(categoryConverter, times(1)).convert(any(Category.class));
    }

    @Test
    public void testConversionWithNullCategories() {
        recipe.setCategories(null);
        assertEquals(0, converter.convert(recipe).getCategories().size());
        verify(categoryConverter, never()).convert(any(Category.class));
    }
}