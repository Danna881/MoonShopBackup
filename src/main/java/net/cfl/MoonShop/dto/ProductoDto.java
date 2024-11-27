package net.cfl.MoonShop.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import net.cfl.MoonShop.dto.ImagenDto;
import net.cfl.MoonShop.modelo.Categoria;

@Data
public class ProductoDto {
	private Long id;
	private String nombre;
	private String marca;
	private String descripcion;
	private BigDecimal precio;
	private int stock;
	private Categoria categoria;
	private List<ImagenDto> imagenes;
}

