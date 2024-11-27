package net.cfl.MoonShop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.MoonShop.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	boolean existsByEmail (String email);
}
