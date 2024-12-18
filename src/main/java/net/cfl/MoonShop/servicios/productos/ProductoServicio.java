package net.cfl.MoonShop.servicios.productos;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.MoonShop.excepciones.ProductoNoEncontradoEx;
//import net.cfl.MoonShop.modelo.Producto;
import net.cfl.MoonShop.repositorio.ProductoRepositorio;
import net.cfl.MoonShop.dto.ImagenDto;
import net.cfl.MoonShop.dto.ProductoDto;
import net.cfl.MoonShop.excepciones.RecursoNoEncontradoEx;
import net.cfl.MoonShop.modelo.Categoria;
import net.cfl.MoonShop.modelo.Imagen;
import net.cfl.MoonShop.modelo.Producto;
import net.cfl.MoonShop.repositorio.CategoriaRepositorio;
import net.cfl.MoonShop.repositorio.ImagenRepositorio;
import net.cfl.MoonShop.request.ActualizaProductoReq;
import net.cfl.MoonShop.request.AgregaProductoReq;
import net.cfl.MoonShop.servicios.producto.IProductoServicio;

@Service
@RequiredArgsConstructor
public class ProductoServicio implements IProductoServicio{
	
	private final ProductoRepositorio productoRepositorio;
	private final CategoriaRepositorio categoriaRepositorio;
	private final ImagenRepositorio imagenRepositorio;
	private final ModelMapper modelMapper;
	
	@Override
	public Producto agregaProducto(AgregaProductoReq request) {
		// Comprobar que exista la categoria en la bdd
		// Si existe, la establecemos como categoria del producto
		// Si no existe, la guardamso en la bdd 
		// Y la establecemos como categoria del producto
		Categoria categoria = Optional.ofNullable(categoriaRepositorio.findByNombre(request.getCategoria().getNombre()))
				.orElseGet(() -> {
					Categoria nuevaCategoria = new Categoria(request.getCategoria().getNombre());
					return categoriaRepositorio.save(nuevaCategoria);
				});
		request.setCategoria(categoria);
		return productoRepositorio.save(creaProducto(request, categoria));
	}
	
	private Producto creaProducto(AgregaProductoReq request, Categoria categoria) {
		return new Producto(
				request.getNombre(),
				request.getMarca(),
				request.getDescripcion(),
				request.getPrecio(),
				request.getStock(),
				categoria
				);
	}

	@Override
	public Producto listaProductoPorId(Long id) {
		return productoRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoEx("Producto no encontrado"));
	}

	@Override
	public void borrarProducto(Long id) {
		productoRepositorio.findById(id)
			.ifPresentOrElse(productoRepositorio::delete, 
					() -> new RecursoNoEncontradoEx("Producto no encontrado"));
	}

	@Override
	public Producto actualizaProducto(ActualizaProductoReq request, Long productoId) {
		return productoRepositorio.findById(productoId)
				.map(productoExistente -> actualizaProductoExistente(productoExistente, request))
				.map(productoRepositorio::save)
				.orElseThrow(() -> new RecursoNoEncontradoEx("producto no encontrado"));
	}
	
	public Producto actualizaProductoExistente(Producto productoExistente, ActualizaProductoReq request) {
		productoExistente.setNombre(request.getNombre());
		productoExistente.setMarca(request.getMarca());
		productoExistente.setDescripcion(request.getDescripcion());
		productoExistente.setPrecio(request.getPrecio());
		productoExistente.setStock(request.getStock());
		Categoria categoria = categoriaRepositorio.findByNombre(request.getCategoria().getNombre());
		productoExistente.setCategoria(categoria);
		return productoExistente;
	}

	@Override
	public List<Producto> listarProductos() {
		return productoRepositorio.findAll();
	}

	@Override
	public List<Producto> listarPorCategoria(String categoria) {
		return productoRepositorio.findByCategoriaNombre(categoria);
	}

	@Override
	public List<Producto> listarPorMarca(String marca) {
		return productoRepositorio.findByMarca(marca);
	}

	@Override
	public List<Producto> listarPorMarcaYCategoria(String marca, String categoria) {
		return productoRepositorio.findByMarcaAndCategoriaNombre(marca, categoria);
	}

	@Override
	public List<Producto> listarPorNombre(String nombre) {
		return productoRepositorio.findByNombre(nombre);
	}

	@Override
	public List<Producto> listarPorNombreYMarca(String nombre, String marca) {
		return productoRepositorio.findByNombreAndMarca(nombre, marca);
	}

	@Override
	public Long contarProductosPorNombreYMarca(String nombre, String marca) {
		return productoRepositorio.countByNombreAndMarca(nombre, marca);
	}
	
	@Override
	public List<ProductoDto> traeProductosConvertidos(List<Producto> productos){
		
		return productos.stream().map(this :: convertirAProductoDto).toList();
	}
	
	@Override
	public ProductoDto convertirAProductoDto(Producto producto) {
		ProductoDto productoDto = modelMapper.map(producto, ProductoDto.class);
		List<Imagen> imagenes = imagenRepositorio.findByProductoId(producto.getId());
		List<ImagenDto> imagenesDto = imagenes
				.stream()
				//el mapper esta usando el objeto imagenes, cuando deberia estar utilizando el parametro imagen
				.map(imagen -> modelMapper.map(imagen, ImagenDto.class))
				.toList();
		productoDto.setImagenes(imagenesDto);
		return productoDto;
	}

}


