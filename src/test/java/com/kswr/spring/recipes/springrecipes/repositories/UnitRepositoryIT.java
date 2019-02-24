package com.kswr.spring.recipes.springrecipes.repositories;

import com.kswr.spring.recipes.springrecipes.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitRepositoryIT {

    @Autowired
    UnitRepository unitRepository;

    @Before
    public void setUp() {

    }

    @Test
    @DirtiesContext
    public void findByDescriptionCup() {
        String value = "Cup";
        UnitOfMeasure unit = unitRepository.findByDescription(value).orElse(null);
        assertEquals(value, unit.getDescription());
    }

    @Test
    public void findByDescriptionTeaspoon() {
        String value = "Teaspoon";
        UnitOfMeasure unit = unitRepository.findByDescription(value).orElse(null);
        assertEquals(value, unit.getDescription());
    }

}