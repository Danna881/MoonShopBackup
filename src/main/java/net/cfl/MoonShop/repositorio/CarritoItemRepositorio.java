package net.cfl.MoonShop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.MoonShop.modelo.CarritoItem;

public interface CarritoItemRepositorio extends JpaRepository<CarritoItem, Long> {
	void deleteAllByCarritoId(Long id);
}
