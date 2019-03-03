package com.kswr.spring.recipes.springrecipes.converters;

import com.kswr.spring.recipes.springrecipes.commands.IngredientCommand;
import com.kswr.spring.recipes.springrecipes.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCommandConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomToUomCommandConverter) {
        this.uomToUomCommandConverter = uomToUomCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        if (source.getRecipe() != null) {
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        }
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setUom(uomToUomCommandConverter.convert(source.getUom()));
        return ingredientCommand;


    }
}
