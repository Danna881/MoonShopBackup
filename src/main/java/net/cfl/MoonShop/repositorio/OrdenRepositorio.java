package net.cfl.MoonShop.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.MoonShop.modelo.Orden;

public interface OrdenRepositorio extends JpaRepository<Orden, Long> {
	List<Orden> findByUsuarioId(Long usuarioId);
}

