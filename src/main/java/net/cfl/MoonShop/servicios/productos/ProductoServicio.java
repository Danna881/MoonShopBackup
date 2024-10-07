package net.cfl.MoonShop.servicios.productos;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.MoonShop.excepciones.ProductoNoEncontradoEx;
import net.cfl.MoonShop.modelo.producto;
import net.cfl.MoonShop.repositorio.ProductoRepositorio;

@Service
@RequiredArgsConstructor
public class ProductoServicio implements IProductoServicio{

	private ProductoRepositorio productoRepositorio;
	@Override
	public producto agregaProducto(producto producto) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public List<producto> listaproducto() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public producto listaproductoPorId(Long id) {
		return productoRepositorio.findById(id).orElseThrow(() -> new ProductoNoEncontradoEx("Producto no encontrado"));
	}

	@Override
	public void borrarProducto(long id) {
		productoRepositorio.findById(id)
		.ifPresentOrElse(productoRepositorio::delete, () -> new ProductoNoEncontradoEx("Producto no encontrado"));
		
	}

	@Override
	public void actualizarProducto(producto producto, Long id) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public List<producto> listarProducto() {
		return productoRepositorio.findAll();
	}

	@Override
	public List<producto> listarporcategoria(String categoria) {
		return productoRepositorio.findByAtCategoria(categoria);
	}

	@Override
	public List<producto> listarPorMarca(String marca) {
		return productoRepositorio.findByMarca(marca);
	}

	@Override
	public List<producto> listarPorMarcaYCategoria(String marca, String categoria) {
		return productoRepositorio.findByMarcaYAtCategoria(marca, categoria);
	}

	@Override
	public List<producto> listarPorNombre(String nombre) {
		return productoRepositorio.findBynombre(Nombre);
	}

	@Override
	public List<producto> listarPorNombreYMarca(String nombre, String marca) {
		return productoRepositorio.fidByNombreYMarca(nombre, marca);
	}

	@Override
	public Long contarProductosPorNombreYMarca(String nombre, String marca) {
		return countByNombreYMarca.findByNombreYMarca(nombre, marca);
	
	}
	
	

}
