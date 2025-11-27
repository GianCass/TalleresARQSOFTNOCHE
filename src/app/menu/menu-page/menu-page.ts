import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  CompraApiService,
  PaqueteDTO,
  CompraResponseDTO,
  TarjetaDTO
} from '../../services/compra-api.service';

interface PaqueteUI {
  codigo: string;
  destino: string;
  precio: number;
}

@Component({
  selector: 'app-menu-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu-page.html',
  styleUrl: './menu-page.css',
})
export class MenuPage implements OnInit {

  selectedSection: 'tarjeta' | 'paquetes' | 'carrito' | 'compras' = 'tarjeta';

  cedulaCliente = '';
  clienteId = '';

  tarjetasCliente: TarjetaDTO[] = [];
  tarjetaId: string | null = null;
  tarjetaSeleccionada: TarjetaDTO | null = null;

  saldo = 0;
  montoRecarga = 0;
  mensajeRecarga = '';

  mostrarFormTarjeta = false;

  nuevaTarjetaNumero = '';
  nuevaTarjetaTipo: 'VIRTUAL' | 'FISICA' = 'VIRTUAL';
  nuevaTarjetaFechaVencimiento = '';
  nuevaTarjetaSaldoInicial = 0;

  paquetesDisponibles: PaqueteUI[] = [];

  carrito: PaqueteDTO[] = [];
  totalCarrito = 0;
  mensajeCarrito = '';
  carritoId: string | null = null;

  compras: CompraResponseDTO[] = [];

  constructor(private compraApi: CompraApiService) {}

  ngOnInit(): void {
    let ced = '';
    if (typeof globalThis !== 'undefined' && (globalThis as any).localStorage) {
      ced = (globalThis as any).localStorage.getItem('cedula') ?? '';
    }
    this.cedulaCliente = ced;
    this.clienteId = this.cedulaCliente;

    if (!this.cedulaCliente) {
      return;
    }

    this.cargarTarjetas();
    this.cargarPaquetes();
    this.cargarCarrito();
    this.cargarCompras();
  }

  cargarTarjetas() {
    this.compraApi.getTarjetasByCliente(this.cedulaCliente).subscribe({
      next: (tarjetas) => {
        this.tarjetasCliente = tarjetas ?? [];
        if (this.tarjetasCliente.length > 0) {
          this.tarjetaId = this.tarjetasCliente[0].id!;
          this.tarjetaSeleccionada = this.tarjetasCliente[0];
          this.saldo = this.tarjetaSeleccionada.saldo ?? 0;
        } else {
          this.tarjetaId = null;
          this.tarjetaSeleccionada = null;
          this.saldo = 0;
          this.mostrarFormTarjeta = true;
        }
      }
    });
  }

  onTarjetaSeleccionada() {
    this.tarjetaSeleccionada = this.tarjetasCliente.find(t => t.id === this.tarjetaId) ?? null;
    this.saldo = this.tarjetaSeleccionada?.saldo ?? 0;
  }

  toggleFormTarjeta() {
    this.mostrarFormTarjeta = !this.mostrarFormTarjeta;
  }

  crearTarjeta() {
    const payload: TarjetaDTO = {
      numeroTarjeta: this.nuevaTarjetaNumero,
      tipo: this.nuevaTarjetaTipo,
      saldo: this.nuevaTarjetaSaldoInicial,
      fechaVencimiento: this.nuevaTarjetaFechaVencimiento,
      cedula: this.cedulaCliente
    };

    this.compraApi.agregarTarjeta(payload).subscribe({
      next: (tarjetaCreada) => {
        this.tarjetasCliente.push(tarjetaCreada);
        this.tarjetaId = tarjetaCreada.id!;
        this.tarjetaSeleccionada = tarjetaCreada;
        this.saldo = tarjetaCreada.saldo ?? 0;
        this.nuevaTarjetaNumero = '';
        this.nuevaTarjetaTipo = 'VIRTUAL';
        this.nuevaTarjetaFechaVencimiento = '';
        this.nuevaTarjetaSaldoInicial = 0;
        this.mostrarFormTarjeta = false;
        this.mensajeRecarga = 'Tarjeta agregada correctamente.';
      }
    });
  }

  agregarSaldo() {
    if (!this.tarjetaId) return;
    if (this.montoRecarga <= 0) return;

    this.compraApi.recargarTarjeta(this.tarjetaId, this.montoRecarga).subscribe({
      next: (tarjeta) => {
        this.saldo = tarjeta.saldo ?? 0;
        const idx = this.tarjetasCliente.findIndex(t => t.id === tarjeta.id);
        if (idx >= 0) this.tarjetasCliente[idx].saldo = tarjeta.saldo;
        this.tarjetaSeleccionada = tarjeta;
        this.montoRecarga = 0;
        this.mensajeRecarga = 'Saldo actualizado.';
      }
    });
  }

  cargarPaquetes() {
    this.compraApi.getPaquetes().subscribe({
      next: (lista) => {
        this.paquetesDisponibles = lista.map((p) => ({
          codigo: p.codigo,
          destino: p.destino,
          precio: p.precio,
        }));
      }
    });
  }

  private mapPaqueteUIToDTO(p: PaqueteUI): PaqueteDTO {
    return {
      codigo: p.codigo,
      destino: p.destino,
      precio: p.precio,
      estad: 'EN_PROCESO',
    };
  }

  cargarCarrito() {
    this.compraApi.getOrCreateCarrito(this.clienteId).subscribe({
      next: (carrito) => {
        this.carritoId = carrito.id;
        this.carrito = carrito.items ?? [];
        this.totalCarrito = carrito.total ?? 0;
      }
    });
  }

  agregarAlCarrito(paquete: PaqueteUI) {
    if (!this.carritoId) return;
    const itemDTO = this.mapPaqueteUIToDTO(paquete);

    this.compraApi.agregarItemCarrito(this.carritoId, itemDTO).subscribe({
      next: (carritoActualizado) => {
        this.carrito = carritoActualizado.items ?? [];
        this.totalCarrito = carritoActualizado.total ?? 0;
      }
    });
  }

  quitarDelCarrito(index: number) {
    if (!this.carritoId) return;
    const item = this.carrito[index];
    if (!item) return;

    this.compraApi.eliminarItemCarrito(this.carritoId, item.codigo).subscribe({
      next: (carritoActualizado) => {
        this.carrito = carritoActualizado.items ?? [];
        this.totalCarrito = carritoActualizado.total ?? 0;
      }
    });
  }

  confirmarCompra() {
    if (this.totalCarrito === 0) return;
    if (this.totalCarrito > this.saldo) return;

    const cliente = {
      idCliente: this.cedulaCliente,
      cedula: this.cedulaCliente
    };

    const paquetes = this.carrito.map(p => ({
      codigo: p.codigo,
      destino: p.destino,
      precio: p.precio
    }));

    const compraPayload = {
      cliente,
      paquetes
    };

    this.compraApi.crearCompra(compraPayload).subscribe({
      next: () => {
        this.saldo -= this.totalCarrito;
        if (!this.carritoId) return;
        this.compraApi.vaciarCarrito(this.carritoId).subscribe(() => {
          this.carrito = [];
          this.totalCarrito = 0;
          this.mensajeCarrito = 'Compra realizada exitosamente.';
          this.cargarCompras();
        });
      }
    });
  }

  cargarCompras() {
    this.compraApi.getCompras(0, 20).subscribe({
      next: (page) => {
        this.compras = page.content.filter(c =>
          c.cliente &&
          (c.cliente.cedula === this.cedulaCliente ||
           c.cliente.idCliente === this.cedulaCliente)
        );
      }
    });
  }
}
