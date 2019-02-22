package com.kswr.spring.recipes.springrecipes.controllers;

import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
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
        log.debug("Getting index page");
        return "index";
    }
}
