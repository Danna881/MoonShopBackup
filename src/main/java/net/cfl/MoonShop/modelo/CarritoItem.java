package net.cfl.MoonShop.modelo;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.cfl.MoonShop.modelo.Carrito;
import net.cfl.MoonShop.modelo.Producto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarritoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int cantidad;
	private BigDecimal precioUni;
	private BigDecimal precioTot;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "carrito_id")
	private Carrito carrito;
	
	
	public void setPrecioTot () {
		this.precioTot = this.precioUni.multiply(new BigDecimal(cantidad));
	}
}