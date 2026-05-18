import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Recipe } from '../../models/recipe.model';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-recipe-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './recipe-detail.component.html',
  styleUrl: './recipe-detail.component.css'
})
export class RecipeDetailComponent implements OnInit {
  recipe?: Recipe;
  loading = true;
  errorMessage = '';

  constructor(
    private readonly route: ActivatedRoute,
    private readonly router: Router,
    private readonly recipeService: RecipeService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (!id) {
      this.errorMessage = 'Receita nao encontrada.';
      this.loading = false;
      return;
    }

    this.recipeService.buscarPorId(id).subscribe({
      next: (recipe) => {
        this.recipe = recipe;
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Receita nao encontrada.';
        this.loading = false;
      }
    });
  }

  excluir(): void {
    if (!this.recipe?.id || !confirm('Deseja excluir esta receita?')) {
      return;
    }

    this.recipeService.excluir(this.recipe.id).subscribe({
      next: () => {
        this.router.navigate(['/receitas'], {
          state: { success: 'Receita excluida com sucesso' }
        });
      },
      error: () => {
        this.errorMessage = 'Nao foi possivel excluir a receita.';
      }
    });
  }
}
