package net.cfl.MoonShop.request;

import java.math.BigDecimal;

import lombok.Data;
import net.cfl.MoonShop.modelo.Categoria;

@Data
public class AgregaProductoReq {
	private Long id;
	private String nombre;
	private String marca;
	private String descripcion;
	private BigDecimal precio;
	private int stock;
	private Categoria categoria;
}
