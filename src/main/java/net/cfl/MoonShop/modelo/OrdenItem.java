package net.cfl.MoonShop.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.cfl.MoonShop.modelo.Orden;
import net.cfl.MoonShop.modelo.Producto;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrdenItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int cantidad;
	private BigDecimal precio;
	
	@ManyToOne
	@JoinColumn(name = "orden_id")
	private Orden orden;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	public OrdenItem(Orden orden, Producto producto, int cantidad, BigDecimal precio) {
		this.orden = orden;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
}