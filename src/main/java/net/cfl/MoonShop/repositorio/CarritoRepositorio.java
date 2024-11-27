package net.cfl.MoonShop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.MoonShop.modelo.Carrito;

public interface CarritoRepositorio extends JpaRepository<Carrito, Long> {
	Carrito findByUsuarioId(Long id);
}
