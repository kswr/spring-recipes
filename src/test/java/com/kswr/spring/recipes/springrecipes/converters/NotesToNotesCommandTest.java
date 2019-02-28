package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private NotesToNotesCommand converter;
    private Notes notes;
    private static final Long ID = 1L;
    private static final String RECIPE_NOTES = "Test recipe notes";

    @Before
    public void setUp() {
        converter = new NotesToNotesCommand();
        notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(notes));
    }

    @Test
    public void testConversionId() {
        assertEquals(ID, converter.convert(notes).getId());
    }

    @Test
    public void testConversionRecipeNotes() {
        assertEquals(RECIPE_NOTES, converter.convert(notes).getRecipeNotes());
    }
}