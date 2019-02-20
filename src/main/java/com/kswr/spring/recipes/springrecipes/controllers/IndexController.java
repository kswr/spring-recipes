package com.kswr.spring.recipes.springrecipes.controllers;

import com.kswr.spring.recipes.springrecipes.domain.Category;
import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import com.kswr.spring.recipes.springrecipes.repositories.CategoryRepository;
import com.kswr.spring.recipes.springrecipes.repositories.UnitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitRepository unitRepository;

    public IndexController(CategoryRepository categoryRepository, UnitRepository unitRepository) {
        this.categoryRepository = categoryRepository;
        this.unitRepository = unitRepository;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getIndexPage(Model model) {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOptional = unitRepository.findByDescription("Teaspoon");
        System.out.println("Cat ID is " + categoryOptional.get().getId());
        System.out.println("UOM ID is " + unitOptional.get().getId());
//        model.addAttribute("category", categoryOptional);
        return "index";
    }
}
