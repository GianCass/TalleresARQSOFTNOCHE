import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { AuthRequest, AuthResponse } from './auth.models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);

  private readonly baseUrl = 'http://localhost:8082/auth';
  private readonly TOKEN_KEY = 'auth_token';

  login(credentials: AuthRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.baseUrl}/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem(this.TOKEN_KEY, response.token);
        }),
        catchError(err => {
          const backend = err?.error?.message;

          let message = 'Ocurrió un error inesperado';

          if (backend?.includes('password'))
            message = 'La contraseña es incorrecta';

          else if (backend?.includes('User') || backend?.includes('usuario'))
            message = 'No existe una cuenta con este correo';

          else if (err.status === 0)
            message = 'No se pudo conectar al servidor';

          return throwError(() => message);
        })
      );
  }

  register(data: any) {
    return this.http.post<AuthResponse>(`${this.baseUrl}/register`, data)
      .pipe(
        catchError(err => {
          const backend = err?.error?.message;

          let message = 'No se pudo registrar el usuario';

          if (backend?.includes('exists') || backend?.includes('ya registrado'))
            message = 'Este correo ya está registrado';

          return throwError(() => message);
        })
      );
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
