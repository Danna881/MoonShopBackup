package net.cfl.MoonShop.servicios.productos;

import java.util.List;

import net.cfl.MoonShop.modelo.producto;

public interface IProductoServicio {
	producto agregaProducto(producto producto);
	List<producto> listaproducto();
	producto listaproductoPorId(Long id);
	void borrarProducto(long id);
	void actualizarProducto(producto producto, Long id);
	List<producto> listarProducto();
	List<producto> listarporcategoria(String categoria);
	List<producto> listarPorMarca(String marca);
	List<producto> listarPorMarcaYCategoria(String marca, String categoria);
	List<producto> listarPorNombre(String nombre);
	List<producto> listarPorNombreYMarca(String nombre, String marca);
	Long contarProductosPorNombreYMarca(String nombre, String marca);
}
