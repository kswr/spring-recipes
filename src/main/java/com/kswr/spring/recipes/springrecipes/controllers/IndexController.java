package com.kswr.spring.recipes.springrecipes.controllers;

import com.kswr.spring.recipes.springrecipes.domain.Category;
import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import com.kswr.spring.recipes.springrecipes.repositories.CategoryRepository;
import com.kswr.spring.recipes.springrecipes.repositories.UnitRepository;
import com.kswr.spring.recipes.springrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getIndexPage(Model model) {
        Set<Recipe> recipes = recipeService.getAll();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}
