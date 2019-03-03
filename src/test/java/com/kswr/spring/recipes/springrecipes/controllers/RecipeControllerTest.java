package com.kswr.spring.recipes.springrecipes.controllers;

import com.kswr.spring.recipes.springrecipes.commands.RecipeCommand;
import com.kswr.spring.recipes.springrecipes.domain.Recipe;
import com.kswr.spring.recipes.springrecipes.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    private RecipeController recipeController;
    private RecipeCommand recipeCommand;
    private Recipe recipe;
    private MockMvc mockMvc;

    @Mock
    private RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        recipe = Recipe.builder().id(1L).build();
        recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        when(recipeService.saveRecipeCommand(any(RecipeCommand.class))).thenReturn(recipeCommand);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
    }

    @Test
    public void testGetRecipeStatus() throws Exception {
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRecipeViewName() throws Exception {
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void testPostNewRecipeStatus() throws Exception {
        mockMvc.perform(post("/recipe")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("id", "")
        .param("description", "Test description"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void testPostNewRecipeViewName() throws Exception {
        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "Test description"))
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    public void testGetUpdateRecipeStatus() throws Exception {
        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUpdateRecipeViewName() throws Exception {
        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void testGetUpdateRecipeAttributeExists() throws Exception {
        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testDeleteStatus() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testDeleteViewName() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(view().name("redirect:/"));
    }
}