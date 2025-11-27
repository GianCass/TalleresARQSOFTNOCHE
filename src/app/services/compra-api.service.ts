import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { environment } from '../../environments/environment';

export interface TarjetaDTO {
  id?: string;
  numeroTarjeta: string;
  fechaVencimiento: string;
  saldo: number;
  tipo: string;
  cedula: string;
}

export interface RecargaTarjetaDTO {
  tarjetaId: string;
  monto: number;
}

export interface PaqueteApi {
  codigo: string;
  destino: string;
  precio: number;
  estadoValidacion?: string;
}

export interface PaqueteDTO {
  codigo: string;
  destino: string;
  precio: number;
  estad: string;
}

export interface CarritoRequestDTO {
  clienteId: string;
  items: PaqueteDTO[];
}

export interface CarritoResponseDTO {
  id: string;
  clienteId: string;
  items: PaqueteDTO[];
  total: number;
  totalItems: number;
}

export interface ClienteCompraDTO {
  idCliente: string;
  cedula?: string;
  nombre?: string;
}

export interface CompraResponseDTO {
  id: string;
  fechaRegistro: string;
  cliente: ClienteCompraDTO;
  paquetes: PaqueteApi[];
  precioTotal: number;
  estado: string;
}

export interface PageCompra {
  content: CompraResponseDTO[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

@Injectable({ providedIn: 'root' })
export class CompraApiService {
  private baseUrl = environment.apiCompraUrl;

  constructor(private http: HttpClient) {}

  getTarjetasByCliente(cedulaCliente: string): Observable<TarjetaDTO[]> {
    return this.http.get<TarjetaDTO[]>(`${this.baseUrl}/api/tarjetas/por-cliente/${cedulaCliente}`);
  }

  recargarTarjeta(tarjetaId: string, monto: number): Observable<TarjetaDTO> {
    const body: RecargaTarjetaDTO = { tarjetaId, monto };
    return this.http.post<TarjetaDTO>(`${this.baseUrl}/api/tarjetas/recarga`, body);
  }

  agregarTarjeta(t: TarjetaDTO): Observable<TarjetaDTO> {
    return this.http.post<TarjetaDTO>(`${this.baseUrl}/api/tarjetas`, t);
  }

  getPaquetes(): Observable<PaqueteApi[]> {
    return this.http.get<PaqueteApi[]>(`${this.baseUrl}/api/paquetes`);
  }

  getCarritoByCliente(clienteId: string): Observable<CarritoResponseDTO> {
    return this.http.get<CarritoResponseDTO>(`${this.baseUrl}/api/carritos/by-cliente/${clienteId}`);
  }

  crearCarrito(clienteId: string): Observable<CarritoResponseDTO> {
    const body: CarritoRequestDTO = { clienteId, items: [] };
    return this.http.post<CarritoResponseDTO>(`${this.baseUrl}/api/carritos`, body);
  }

  getOrCreateCarrito(clienteId: string): Observable<CarritoResponseDTO> {
    return this.getCarritoByCliente(clienteId).pipe(catchError(() => this.crearCarrito(clienteId)));
  }

  agregarItemCarrito(carritoId: string, item: PaqueteDTO): Observable<CarritoResponseDTO> {
    return this.http.post<CarritoResponseDTO>(`${this.baseUrl}/api/carritos/${carritoId}/items`, item);
  }

  eliminarItemCarrito(carritoId: string, codigo: string): Observable<CarritoResponseDTO> {
    return this.http.delete<CarritoResponseDTO>(`${this.baseUrl}/api/carritos/${carritoId}/items/${codigo}`);
  }

  vaciarCarrito(carritoId: string): Observable<CarritoResponseDTO> {
    return this.http.delete<CarritoResponseDTO>(`${this.baseUrl}/api/carritos/${carritoId}/items`);
  }

  crearCompra(body: any): Observable<CompraResponseDTO> {
    return this.http.post<CompraResponseDTO>(`${this.baseUrl}/api/compra`, body);
  }

  getCompras(page = 0, size = 20): Observable<PageCompra> {
    return this.http.get<PageCompra>(`${this.baseUrl}/api/compra?page=${page}&size=${size}`);
  }
}
