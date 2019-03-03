package com.kswr.spring.recipes.springrecipes.controllers;

import com.kswr.spring.recipes.springrecipes.commands.IngredientCommand;
import com.kswr.spring.recipes.springrecipes.services.IngredientService;
import com.kswr.spring.recipes.springrecipes.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    private IngredientController ingredientController;
    private MockMvc mockMvc;
    private IngredientCommand ingredientCommand;

    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService);
        ingredientCommand = new IngredientCommand();
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        when(recipeService.findById(anyLong())).thenReturn(null);
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredientCommand);
    }

    @Test
    public void testGetListIngredientsStatus() throws Exception{
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetListIngredientsViewName() throws Exception{
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(view().name("recipe/ingredient/list"));
    }

    @Test
    public void testGetListIngredientsVerifyServiceCall() throws Exception{
        mockMvc.perform(get("/recipe/1/ingredients"));
        verify(recipeService, times(1)).findById(anyLong());
    }

    @Test
    public void testShowIngredientsStatus() throws Exception {
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowIngredientViewName() throws Exception {
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(view().name("recipe/ingredient/show"));
    }

    @Test
    public void testShowIngredientExpectAttribute() throws Exception {
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(model().attributeExists("ingredient"));
    }
}