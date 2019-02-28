package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.CategoryCommand;
import com.kswr.spring.recipes.springrecipes.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory converter;
    private CategoryCommand categoryCommand;
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "CategoryCommand test description";

    @Before
    public void setUp() {
        converter = new CategoryCommandToCategory();
        categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void testConversion() {
        assertEquals(ID_VALUE, converter.convert(categoryCommand).getId());
    }

    @Test
    public void testConversionDescription() {
        assertEquals(DESCRIPTION, converter.convert(categoryCommand).getDescription());
    }
}