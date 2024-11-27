package net.cfl.MoonShop.servicios.carrito;

import net.cfl.MoonShop.modelo.CarritoItem;

public interface ICarritoItemServicio {
	void agregaItemAlCarrito(Long carritoId, Long productoId, int cantidad);
	void quitaItemDelCarrito(Long carritoId, Long productoId);
	void actualizaCantidadItem(Long carritoId, Long productoId, int cantidad);
	CarritoItem traeCarritoItem(Long carritoId, Long productoId);
	

}
