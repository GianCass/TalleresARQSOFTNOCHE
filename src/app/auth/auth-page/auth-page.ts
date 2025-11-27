import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-auth-page',
  templateUrl: './auth-page.html',
  styleUrl: './auth-page.css',
  imports: [CommonModule, ReactiveFormsModule]
})
export class AuthPageComponent {

  mode: 'login' | 'register' = 'login';

  errorMessage: string | null = null;

  private fb = inject(FormBuilder);
  private auth = inject(AuthService);
  private router = inject(Router);

  form = this.fb.group({
    id: [''],
    nombreCompleto: [''],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });

  onSubmit() {
    this.errorMessage = null;

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      this.errorMessage = 'Por favor completa correctamente todos los campos.';
      return;
    }

    if (this.mode === 'login') {
      const email = this.form.value.email!;
      const password = this.form.value.password!;

      this.auth.login({ email, password }).subscribe({
        next: (resp) => {

          localStorage.setItem('cedula', email); 
          localStorage.setItem('clienteId', email);  

          this.router.navigate(['/menu']);
        },
        error: (err) => {
          this.errorMessage = this.extractError(err);
        }
      });

    } else {
      this.auth.register(this.form.value).subscribe({
        next: () => this.mode = 'login',
        error: (err) => {
          this.errorMessage = this.extractError(err);
        }
      });
    }
  }

  private extractError(err: any): string {
    if (err.error && err.error.message) return err.error.message;
    if (err.error && typeof err.error === 'string') return err.error;
    return 'Ocurrió un error, por favor inténtalo de nuevo';
  }

}
