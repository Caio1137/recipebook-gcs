package com.recipebook.repository;

import com.recipebook.entity.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByOrderByDataCadastroDesc();

    boolean existsByNomeIgnoreCase(String nome);
}
