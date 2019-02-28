package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.NotesCommand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// TODO implement test
public class NotesCommandToNotesTest {

    private NotesCommandToNotes converter;
    private NotesCommand notesCommand;
    private static final Long ID = 1L;
    private static final String RECIPE_NOTES = "Test recipe notes";

    @Before
    public void setUp() {
        converter = new NotesCommandToNotes();
        notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(notesCommand));
    }

    @Test
    public void testConversionId() {
        assertEquals(ID, converter.convert(notesCommand).getId());
    }

    @Test
    public void testConversionRecipeNotes() {
        assertEquals(RECIPE_NOTES, converter.convert(notesCommand).getRecipeNotes());
    }
}