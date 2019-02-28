package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.UnitOfMeasureCommand;
import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private UnitOfMeasureCommandToUnitOfMeasure converter;
    private UnitOfMeasureCommand uomCommand;
    private final static Long ID = 1L;
    private final static String DESCRIPTION = "UnitOfMeasureCommand test description";

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
        uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(ID);
        uomCommand.setDescription(DESCRIPTION);
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(uomCommand));
    }

    @Test
    public void testConversionId() {
        assertEquals(ID, converter.convert(uomCommand).getId());
    }

    @Test
    public void testConversionDescription() {
        assertEquals(DESCRIPTION, converter.convert(uomCommand).getDescription());
    }

}