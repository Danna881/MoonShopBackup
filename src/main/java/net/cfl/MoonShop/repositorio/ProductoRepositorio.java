package net.cfl.MoonShop.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.MoonShop.modelo.producto;

public interface ProductoRepositorio extends JpaRepository<producto, Long>{
	List<producto> findByAtCategoria(String categoria);
	List<producto> findByMarca(String marca);
	List<producto> findByMarcaYAtCategoria(String marca, String Categoria);
	List<producto> findByNombre(String Nombre);
	List<producto> fidByNombreYAtMarca(String nombre, String Marca);
	List<producto> countByNombreYMarca(String Nombre, String Marca);
}
