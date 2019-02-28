package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.IngredientCommand;
import com.kswr.spring.recipes.springrecipes.commands.UnitOfMeasureCommand;
import com.kswr.spring.recipes.springrecipes.domain.Ingredient;
import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IngredientCommandToIngredientTest {

    private final static Long ID = 1L;
    private final static String DESCRIPTION = "Test description";
    private final static BigDecimal AMOUNT = new BigDecimal(1000);
    private final static Long UOM_ID = 2L;
    private final static String UOM_DESCRIPTION = "UOM test description";
    private final static Long UOMC_ID = 3L;
    private final static String UOMC_DESCRIPTION = "UOMC test description";
    private UnitOfMeasure uom;
    private UnitOfMeasureCommand uomCommand;
    private IngredientCommand ingredientCommand;
    private IngredientCommandToIngredient converter;

    @Mock
    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        converter = new IngredientCommandToIngredient(uomConverter);
        uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);
        uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOMC_ID);
        uomCommand.setDescription(UOMC_DESCRIPTION);
        ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUom(uomCommand);

        when(uomConverter.convert(any(UnitOfMeasureCommand.class))).thenReturn(uom);
        when(uomConverter.convert(null)).thenReturn(null);
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
        verify(uomConverter,never()).convert(any());
    }

    @Test
    public void testNotNullObject() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        assertNotNull(converter.convert(ingredientCommand));
        verify(uomConverter,times(1)).convert(any());

    }

    @Test
    public void testConversionId() {
        Ingredient ingredient = converter.convert(ingredientCommand);
        assertEquals(ID, ingredient.getId());
    }

    @Test
    public void testConversionDescription() {
        Ingredient ingredient = converter.convert(ingredientCommand);
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }

    @Test
    public void testConversionAmount() {
        Ingredient ingredient = converter.convert(ingredientCommand);
        assertEquals(AMOUNT, ingredient.getAmount());
    }

    @Test
    public void testConversionUom() {
        Ingredient ingredient = converter.convert(ingredientCommand);
        verify(uomConverter, times(1)).convert(any(UnitOfMeasureCommand.class));
        assertEquals(UOM_ID, ingredient.getUom().getId());
        assertEquals(UOM_DESCRIPTION, ingredient.getUom().getDescription());
    }

    @Test
    public void testConversionWithNullUom() {
        ingredientCommand.setUom(null);
        Ingredient ingredient = converter.convert(ingredientCommand);
        assertNull(ingredient.getUom());
    }
}