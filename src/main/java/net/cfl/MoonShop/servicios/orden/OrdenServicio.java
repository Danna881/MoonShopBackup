package net.cfl.MoonShop.servicios.orden;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.cfl.MoonShop.dto.OrdenDto;
import net.cfl.MoonShop.enums.OrdenEstado;
import net.cfl.MoonShop.excepciones.RecursoNoEncontradoEx;
import net.cfl.MoonShop.modelo.Carrito;
import net.cfl.MoonShop.modelo.Orden;
import net.cfl.MoonShop.modelo.OrdenItem;
import net.cfl.MoonShop.repositorio.OrdenRepositorio;
import net.cfl.MoonShop.repositorio.ProductoRepositorio;
import net.cfl.MoonShop.servicios.carrito.CarritoServicio;
import net.cfl.MoonShop.servicios.orden.IOrdenServicio;

@Service
@RequiredArgsConstructor
public class OrdenServicio implements IOrdenServicio{
	private final OrdenRepositorio ordenRepositorio;
	private final ProductoRepositorio productoRepositorio;
	private final CarritoServicio carritoServicio;
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Orden realizaOrden(Long usuarioId) {
		Carrito carrito = carritoServicio.traeCarritoPorUsuarioId(usuarioId);
		Orden orden = crearOrden(carrito);
		List<OrdenItem> listaDeItemsOrden =  crearOrdenItem(orden, carrito);
		
		orden.setOrdenItems(new HashSet<>(listaDeItemsOrden));
		orden.setMontoTotal(calculaMontoTotal(listaDeItemsOrden));
		Orden ordenGuardada = ordenRepositorio.save(orden);
		carritoServicio.limpiaCarrito(carrito.getId());
		
		return ordenGuardada;
	}
	
	
	private List<OrdenItem> crearOrdenItem(Orden orden, Carrito carrito){
		return carrito.getCarritoItems().stream().map(carritoItem -> {
			Producto producto = carritoItem.getProducto();
			producto.setStock(producto.getStock() - carritoItem.getCantidad());
			productoRepositorio.save(producto);
			return new OrdenItem(
					orden, 
					producto, 
					carritoItem.getCantidad(), 
					carritoItem.getPrecioUni());
		}).toList();
	} 
	
	private Orden crearOrden(Carrito carrito) {
		Orden orden = new Orden();
		orden.setUsuario(carrito.getUsuario());
		orden.setOrdenEstado(OrdenEstado.PENDIENTE);
		orden.setOrdenFecha(LocalDate.now());
		return orden;
	}
	
	
	private BigDecimal calculaMontoTotal(List<OrdenItem> listaDeItems) {
		return listaDeItems
				.stream()
				.map(item -> item.getPrecio()
						.multiply(new BigDecimal(item.getCantidad())))
				.reduce(BigDecimal.ZERO, BigDecimal :: add);
		
	}
	

	@Override
	public OrdenDto traeOrden(Long ordenId) {
		return ordenRepositorio.findById(ordenId)
				.map(this :: convertirAOrdenDto)
				.orElseThrow(() -> new RecursoNoEncontradoEx("Orden no encontrada"));
	}
	
	@Override
	public List<OrdenDto> traeUsuarioOrdenes(Long usuarioId){
		List<Orden> ordenes = ordenRepositorio.findByUsuarioId(usuarioId);
		return ordenes.stream().map(this :: convertirAOrdenDto).toList();
	}

	private OrdenDto convertirAOrdenDto(Orden orden) {
		return modelMapper.map(orden, OrdenDto.class);
	} 
}
