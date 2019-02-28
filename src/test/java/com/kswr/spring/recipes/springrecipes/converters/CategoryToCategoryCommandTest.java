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
    private static final Long ID = 1L;
    private static final String DESCRIPTION_VALUE = "Test description";

    @Before
    public void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));

    }

    @Test
    public void testConversion() {
        // todo refactor to 2 separate tests
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION_VALUE);
        CategoryCommand converted = converter.convert(category);
        assertEquals(ID, converted.getId());
        assertEquals(DESCRIPTION_VALUE, converted.getDescription());
    }
}