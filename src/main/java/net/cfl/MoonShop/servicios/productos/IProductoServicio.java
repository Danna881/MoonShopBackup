package net.cfl.MoonShop.servicios.productos;

import java.util.List;

import net.cfl.MoonShop.dto.ProductoDto;
import net.cfl.MoonShop.modelo.Producto;
import net.cfl.MoonShop.request.ActualizaProductoReq;
import net.cfl.MoonShop.request.AgregaProductoReq;


public interface IProductoServicio {
	//crud
	Producto agregaProducto(AgregaProductoReq request);
	Producto listaProductoPorId(Long id);
	void borrarProducto(Long id);
	Producto actualizaProducto(ActualizaProductoReq request, Long id);
	
	//filtros
	List<Producto> listarProductos();
	List<Producto> listarPorCategoria(String categoria);
	List<Producto> listarPorMarca(String marca);
	List<Producto> listarPorMarcaYCategoria(String marca, String categoria);
	List<Producto> listarPorNombre(String nombre);
	List<Producto> listarPorNombreYMarca(String nombre, String marca);
	Long contarProductosPorNombreYMarca(String nombre, String marca);
	
	//mapper
	ProductoDto convertirAProductoDto(Producto producto);
	List<ProductoDto> traeProductosConvertidos(List<Producto> productos);
	
	
}
