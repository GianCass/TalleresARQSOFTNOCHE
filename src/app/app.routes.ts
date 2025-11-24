import { Routes } from '@angular/router';
import { AuthPageComponent } from './auth/auth-page/auth-page';
import { DashboardComponent } from './dashboard/dashboard';

export const routes: Routes = [
  { path: '', component: AuthPageComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '**', redirectTo: '' }
];
