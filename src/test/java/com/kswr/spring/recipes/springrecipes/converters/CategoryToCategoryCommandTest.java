package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.CategoryCommand;
import com.kswr.spring.recipes.springrecipes.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryToCategoryCommandTest {

    private CategoryToCategoryCommand converter;
    private Category category;
    private static final Long ID = 1L;
    private static final String DESCRIPTION_VALUE = "Category test description";

    @Before
    public void setUp() {
        converter = new CategoryToCategoryCommand();
        category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION_VALUE);
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(category));
    }

    @Test
    public void testConversionId() {
        assertEquals(ID, converter.convert(category).getId());
    }

    @Test
    public void testConversionDescription() {
        assertEquals(DESCRIPTION_VALUE, converter.convert(category).getDescription());
    }
}