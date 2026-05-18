import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Recipe } from '../../models/recipe.model';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-recipe-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './recipe-list.component.html',
  styleUrl: './recipe-list.component.css'
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [];
  searchTerm = '';
  loading = true;
  errorMessage = '';
  successMessage = '';

  constructor(private readonly recipeService: RecipeService) {}

  ngOnInit(): void {
    this.successMessage = history.state?.success ?? '';
    this.carregarReceitas();
  }

  get filteredRecipes(): Recipe[] {
    const term = this.searchTerm.trim().toLowerCase();

    if (!term) {
      return this.recipes;
    }

    return this.recipes.filter((recipe) => recipe.nome.toLowerCase().includes(term));
  }

  carregarReceitas(): void {
    this.loading = true;
    this.recipeService.listar().subscribe({
      next: (recipes) => {
        this.recipes = recipes;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Nao foi possivel carregar as receitas.';
        this.loading = false;
      }
    });
  }

  trackById(_: number, recipe: Recipe): number | undefined {
    return recipe.id;
  }
}
