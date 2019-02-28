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

public class IngredientToIngredientCommandTest {
    private IngredientToIngredientCommand converter;
    private Ingredient ingredient;
    private IngredientCommand ingredientCommand;
    private final static Long ID = 1L;
    private final static String DESCRIPTION = "Ingredient test description";
    private final static BigDecimal AMOUNT = new BigDecimal(9999);

    @Mock
    private UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Mock
    private UnitOfMeasureCommand uomCommand;

    @Mock
    private UnitOfMeasure uom;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        converter = new IngredientToIngredientCommand(uomConverter);
        when(uomConverter.convert(any(UnitOfMeasure.class))).thenReturn(uomCommand);
        ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(uom);
        ingredientCommand = null;
    }

    @Test
    public void testConversionNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testConversionNotNull() {
        assertNotNull(converter.convert(ingredient));
    }

    @Test
    public void testConversionId() {
        ingredientCommand = converter.convert(ingredient);
        assertEquals(ID, ingredientCommand.getId());
    }

    @Test
    public void testConversionDescription() {
        ingredientCommand = converter.convert(ingredient);
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    public void testConversionAmount() {
        ingredientCommand = converter.convert(ingredient);
        assertEquals(AMOUNT, ingredient.getAmount());
    }

    @Test
    public void testConversionUom() {
        ingredientCommand = converter.convert(ingredient);
        assertNotNull(ingredientCommand.getUom());
    }

    @Test
    public void testConversionWithNullUom() {
        ingredient.setUom(null);
        ingredientCommand = converter.convert(ingredient);
        assertNull(ingredientCommand.getUom());
        verify(uomConverter,times(1)).convert(any());
    }
}