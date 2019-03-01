package com.kswr.spring.recipes.springrecipes.controllers;

import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class IndexControllerTest {

    private IndexController indexController;
    private MockMvc mockMvc;
    private Recipe recipe;
    private Set<Recipe> recipes;

    @Mock
    private Model model;

    @Mock
    private RecipeService recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        recipe = new Recipe();
        recipe.setId(1L);
        recipes = new HashSet<>();
        recipes.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipes);
    }

    @Test
    public void testGetIndexStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetIndexViewName() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }
}