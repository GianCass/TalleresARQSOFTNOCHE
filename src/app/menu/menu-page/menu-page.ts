import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Paquete {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
}

interface Compra {
  id: number;
  fecha: Date;
  paquetes: Paquete[];
  total: number;
}

@Component({
  selector: 'app-menu-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu-page.html',
  styleUrl: './menu-page.css',
})
export class MenuPage {
  // pestaña actual
  selectedSection: 'tarjeta' | 'paquetes' | 'carrito' | 'compras' = 'tarjeta';

  // --- Tarjeta ---
  saldo = 0;
  montoRecarga = 0;
  mensajeRecarga = '';

  // --- Paquetes / Carrito / Compras ---
  paquetesDisponibles: Paquete[] = [
    { id: 1, nombre: 'Paquete Básico', descripcion: 'Incluye X y Y', precio: 10 },
    { id: 2, nombre: 'Paquete Premium', descripcion: 'Más beneficios', precio: 25 },
    { id: 3, nombre: 'Paquete Viaje', descripcion: 'Beneficios para viajes', precio: 40 },
  ];

  carrito: Paquete[] = [];
  compras: Compra[] = [];
  private idCompra = 1;
  mensajeCarrito = '';

  // --- Tarjeta ---
  agregarSaldo() {
    if (this.montoRecarga <= 0) {
      this.mensajeRecarga = 'Ingresa un monto válido.';
      return;
    }
    this.saldo += this.montoRecarga;
    this.mensajeRecarga = `Se agregaron $${this.montoRecarga} a tu tarjeta.`;
    this.montoRecarga = 0;
  }

  // --- Carrito ---
  agregarAlCarrito(paquete: Paquete) {
    this.carrito.push(paquete);
    this.mensajeCarrito = '';
  }

  quitarDelCarrito(index: number) {
    this.carrito.splice(index, 1);
  }

  get totalCarrito(): number {
    return this.carrito.reduce((acc, p) => acc + p.precio, 0);
  }

  confirmarCompra() {
    const total = this.totalCarrito;

    if (total === 0) {
      this.mensajeCarrito = 'El carrito está vacío.';
      return;
    }
    if (total > this.saldo) {
      this.mensajeCarrito = 'No hay saldo suficiente en la tarjeta.';
      return;
    }

    this.saldo -= total;

    this.compras.push({
      id: this.idCompra++,
      fecha: new Date(),
      paquetes: [...this.carrito],
      total,
    });

    this.carrito = [];
    this.mensajeCarrito = 'Compra realizada con éxito.';
  }
}
