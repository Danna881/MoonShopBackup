package net.cfl.MoonShop.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.MoonShop.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
	List<Producto> findByAtCategoria(String categoria);
	List<Producto> findByMarca(String marca);
	List<Producto> findByMarcaYAtCategoria(String marca, String Categoria);
	List<Producto> findByNombre(String Nombre);
	List<Producto> fidByNombreYAtMarca(String nombre, String Marca);
	List<Producto> countByNombreYMarca(String Nombre, String Marca);
}
