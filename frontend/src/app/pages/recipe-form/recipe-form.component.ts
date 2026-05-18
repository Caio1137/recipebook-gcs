import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CATEGORIAS, Categoria, Recipe } from '../../models/recipe.model';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-recipe-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './recipe-form.component.html',
  styleUrl: './recipe-form.component.css'
})
export class RecipeFormComponent {
  readonly categorias = CATEGORIAS;
  saving = false;
  serverError = '';

  form = this.formBuilder.nonNullable.group({
    nome: ['', [Validators.required, Validators.minLength(3)]],
    categoria: ['', [Validators.required]],
    tempoPreparo: [1, [Validators.required, Validators.min(1)]],
    porcoes: [1, [Validators.required]],
    ingredientes: ['', [Validators.required, this.ingredientesValidator]],
    modoPreparo: ['', [Validators.required, Validators.minLength(10)]]
  });

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly recipeService: RecipeService,
    private readonly router: Router
  ) {}

  submit(): void {
    this.serverError = '';

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.saving = true;
    this.recipeService.criar(this.toRecipe()).subscribe({
      next: () => {
        this.router.navigate(['/receitas'], {
          state: { success: 'Receita cadastrada com sucesso' }
        });
      },
      error: (error: HttpErrorResponse) => {
        this.serverError = error.error?.mensagem ?? 'Nao foi possivel salvar a receita.';
        this.saving = false;
      }
    });
  }

  control(name: string): AbstractControl {
    return this.form.get(name) as AbstractControl;
  }

  showError(name: string): boolean {
    const field = this.control(name);
    return field.invalid && (field.dirty || field.touched);
  }

  private ingredientesValidator(control: AbstractControl): ValidationErrors | null {
    const value = String(control.value ?? '');
    const ingredientes = value.split('\n').map((item) => item.trim()).filter(Boolean);

    return ingredientes.length > 0 ? null : { ingredientes: true };
  }

  private toRecipe(): Recipe {
    const raw = this.form.getRawValue();

    return {
      nome: raw.nome.trim(),
      categoria: raw.categoria as Categoria,
      tempoPreparo: Number(raw.tempoPreparo),
      porcoes: Number(raw.porcoes),
      ingredientes: raw.ingredientes.split('\n').map((item) => item.trim()).filter(Boolean),
      modoPreparo: raw.modoPreparo.trim()
    };
  }
}
