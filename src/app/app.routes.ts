import { Routes } from '@angular/router';
import { AuthPageComponent } from './auth/auth-page/auth-page';
import { MenuPage } from './menu/menu-page/menu-page';

export const routes: Routes = [
  { path: 'auth', component: AuthPageComponent },
  { path: 'menu', component: MenuPage },
  { path: '', redirectTo: 'auth', pathMatch: 'full' },
  { path: '**', redirectTo: 'auth' }
];
