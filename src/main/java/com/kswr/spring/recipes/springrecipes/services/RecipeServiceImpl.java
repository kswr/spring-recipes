package com.kswr.spring.recipes.springrecipes.services;

import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAll() {
        Set<Recipe> recipeSet = new HashSet();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
