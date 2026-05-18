package com.recipebook.controller;

import com.recipebook.entity.Recipe;
import com.recipebook.repository.RecipeRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/receitas")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> listar() {
        return recipeRepository.findAllByOrderByDataCadastroDesc();
    }

    @GetMapping("/{id}")
    public Recipe buscarPorId(@PathVariable Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita nao encontrada"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe criar(@Valid @RequestBody Recipe recipe) {
        if (recipeRepository.existsByNomeIgnoreCase(recipe.getNome())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ja existe uma receita com este nome");
        }

        recipe.setId(null);
        recipe.setDataCadastro(null);
        recipe.setNome(recipe.getNome().trim());
        recipe.setModoPreparo(recipe.getModoPreparo().trim());
        recipe.setIngredientes(recipe.getIngredientes().stream()
                .map(String::trim)
                .toList());

        return recipeRepository.save(recipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita nao encontrada");
        }

        recipeRepository.deleteById(id);
    }
}
