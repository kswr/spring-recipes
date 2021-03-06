package com.kswr.spring.recipes.springrecipes.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;

    @Builder
    public IngredientCommand(Long id, Long recipeId, String description, BigDecimal amount, UnitOfMeasureCommand uom) {
        this.id = id;
        this.recipeId = recipeId;
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
}
