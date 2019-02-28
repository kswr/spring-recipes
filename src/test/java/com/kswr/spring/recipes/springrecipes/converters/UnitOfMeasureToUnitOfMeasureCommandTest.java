package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private UnitOfMeasureToUnitOfMeasureCommand converter;
    private UnitOfMeasure uom;
    private final static Long ID = 1L;
    private final static String DESCRIPTION = "UnitOfMeasure test description";

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
        uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(uom));
    }

    @Test
    public void testConversionId() {
        assertEquals(ID, converter.convert(uom).getId());
    }

    @Test
    public void testConversionDescription() {
        assertEquals(DESCRIPTION, converter.convert(uom).getDescription());
    }

}
