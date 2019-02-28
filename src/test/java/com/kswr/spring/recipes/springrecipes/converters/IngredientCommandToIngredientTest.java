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
    private IngredientCommandToIngredient converter;

    @Mock
    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        converter = new IngredientCommandToIngredient(uomConverter);
    }

    @Test
    public void testNullObject() {
        // then
        assertNull(converter.convert(null));
        verify(uomConverter,never()).convert(any());

    }

    @Test
    public void testNotNullObject() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();

        // then
        assertNotNull(converter.convert(ingredientCommand));
        verify(uomConverter,times(1)).convert(any());

    }

    @Test
    public void testConversion() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);

        // when
        when(uomConverter.convert(any(UnitOfMeasureCommand.class))).thenReturn(uom);
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
        assertEquals(UOM_DESCRIPTION, ingredient.getUom().getDescription());

    }

    @Test
    public void testConversionWithNullUom() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUom(null);

        // when
        when(uomConverter.convert(null)).thenReturn(null);
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNull(ingredient.getUom());
    }
}