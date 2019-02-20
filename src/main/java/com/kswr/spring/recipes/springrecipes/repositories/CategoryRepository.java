package com.kswr.spring.recipes.springrecipes.repositories;

import com.kswr.spring.recipes.springrecipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
