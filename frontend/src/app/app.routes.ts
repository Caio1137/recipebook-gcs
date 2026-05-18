import { Routes } from '@angular/router';
import { RecipeListComponent } from './pages/recipe-list/recipe-list.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'receitas',
    pathMatch: 'full'
  },
  {
    path: 'receitas',
    component: RecipeListComponent
  }
];
