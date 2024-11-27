package net.cfl.MoonShop.servicios.carrito;

import java.math.BigDecimal;

import net.cfl.MoonShop.modelo.Carrito;
import net.cfl.MoonShop.modelo.Usuario;

public interface ICarritoServicio {
	Carrito traeCarrito(Long id);
	void limpiaCarrito(Long id);
	BigDecimal traePrecioTotal(Long id);
	Carrito inicializaCarrito(Usuario usuario);
	Carrito traeCarritoPorUsuarioId(Long usuarioId);
	
}
